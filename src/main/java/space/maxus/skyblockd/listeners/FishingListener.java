package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.objects.*;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;
import space.maxus.skyblockd.util.Singleton;
import space.maxus.skyblockd.util.WeightedList;

import java.util.Locale;
import java.util.Random;

public class FishingListener extends BetterListener {
    @EventHandler
    public void onFish(@NotNull PlayerFishEvent e) {
        if(e.getState() != PlayerFishEvent.State.CAUGHT_FISH || e.getCaught() == null) return;

        Entity caught = e.getCaught();
        Player p = e.getPlayer();
        Random r = new Random();

        PlayerContainer pc = ContainerHelper.getPlayer(p);
        SkillContainer fishing = pc.skills.data.get("fishing");

        double randModifier = r.nextDouble() - 0.3d;
        float modifier = 0.5f+(fishing.currentLevel/10f);

        boolean modifyVanilla = randModifier <= modifier;

        if(!modifyVanilla) return;

        double scc = UniversalHelper.getSeaCreatureChance(p)/100d;
        double randScc = r.nextDouble();

        boolean isEntity = randScc <= scc;

        FishingData fish = SkyblockD.getServerData().fishingData;

        if(isEntity) {
            operateEntity(fishing, fish, r, p, caught);
        } else {
            FishingDrops drops = fish.items;
            Singleton<WeightedList<ItemStack>, Boolean> items = WeightedList.fromFishingData(drops);
            Item i = (Item) caught;
            ItemStack item = items.getFirst().get(r);
            String name;
            if (items.getSecond()) {
                assert item != null;
                name = "ENCHANTED_"+item.getType().name();
            } else {
                name = item.getType().name();
            }
            i.setItemStack(item);
            operateFishingSkill(p, name);
        }
    }

    private void operateEntity(@NotNull SkillContainer fishing, @NotNull FishingData fish, @NotNull Random r, @NotNull Player p, @NotNull Entity caught) {
        Singleton<WeightedList<String>, Boolean> singleton = WeightedList.getFishingSummons(fish.mobs);
        WeightedList<String> entities = singleton.getFirst();
        boolean isSb = singleton.getSecond();
        String name = entities.get(r);
        if(isSb) {
            SeaCreatureContainer sc =  fish.mobs.skyblock.get(name);
            EntitySummon sb = EntitySummon.valueOf(name);
            String formattedName = sb.getEntity().getName();

            int reqLvl = sc.level;
            if(fishing.currentLevel + 1 < reqLvl) {
                p.sendMessage(ChatColor.YELLOW+"You tried to lure out " + ChatColor.MAGIC + formattedName + ChatColor.RESET+""+ChatColor.YELLOW+" but it breaks away! Maybe you need higher "+ChatColor.AQUA+" Fishing "+ChatColor.YELLOW+" level to fish it?");
                p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
                return;
            }

            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1f, 0.5f);
            p.sendMessage(ChatColor.GREEN+SkyblockD.getServerData().fishingMessages.get(name));
            sb.getEntity().generate(caught);
        } else {
            SeaCreatureContainer sc = fish.mobs.vanilla.get(name);
            EntityType type = EntityType.valueOf(name);
            int reqLvl = sc.level;
            assert name != null;
            String formattedName = ChatColor.GREEN + CustomItem.capitalize(name.toLowerCase(Locale.ENGLISH).replace("_", " "));

            if(fishing.currentLevel + 1 < reqLvl) {
                p.sendMessage(ChatColor.YELLOW+"You tried to lure out " + ChatColor.MAGIC + formattedName + ChatColor.RESET+""+ChatColor.YELLOW+" but it breaks away! Maybe you need higher "+ChatColor.AQUA+"Fishing"+ChatColor.YELLOW+" level to fish it?");
                p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 1f);
                return;
            }
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1f, 0.5f);
            LivingEntity summon = (LivingEntity) caught.getWorld().spawnEntity(caught.getLocation(), type);
            summon.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)0);

            summon.setVelocity(p.getLocation().toVector().add(new Vector(-1, 4, 0)));

            p.sendMessage(ChatColor.GREEN+"You lured out " + formattedName);
        }
    }

    private void operateFishingSkill(@NotNull Player p, @NotNull String itemName) {
        int xp = xpFromName(itemName);

        PlayerContainer cont = ContainerHelper.getPlayer(p);

        int lvl = cont.skills.data.get("fishing").currentLevel;

        int tlvl = lvl == 0 ? 1 : lvl;
        float exp = (xp + 5) * SkillHelper.getModifier(tlvl);

        UniversalHelper.giveSkillExperience(p, "fishing", Math.round(exp));
    }

    private int xpFromName(@NotNull String name) {
        switch(name) {
            case "ENCHANTED_DIRT": return 20;

            case "ENCHANTED_ROTTEN_FLESH":
            case "ENCHANTED_TROPICAL_FISH":
            case "ENCHANTED_SALMON":
            case "ENCHANTED_COD": return 45;

            case "ENCHANTED_PRISMARINE_SHARD":
            case "ENCHANTED_LILY_PAD":
            case "ENCHANTED_INK_SAC":
            case "ENCHANTED_PUFFERFISH": return 70;

            case "GOLDEN_APPLE":
            case "ENCHANTED_SPONGE": return 75;

            case "ENCHANTED_DRIED_KELP":
            case "ENCHANTED_COOKED_SALMON":
            case "ENCHANTED_COOKED_COD": return 100;

            default:
                try {
                    Integer exp = SkyblockD.getServerData().fishing.get(name);
                    assert exp != null;
                    return exp;
                } catch(@NotNull NullPointerException | AssertionError e) {
                    return 10;
                }
        }
    }
}
