package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.nms.NMSColor;
import space.maxus.skyblockd.nms.PacketUtils;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SeaCreatureContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.*;

public class KillListener extends BetterListener {
    @EventHandler
    public void onKill(EntityDeathEvent e) {
        LivingEntity en = e.getEntity();
        Player p = en.getKiller();
        if(p == null || p.hasMetadata("NPC")) return;
        List<ItemStack> drops = e.getDrops();
        if(ItemHelper.hasMagnet(p) && !drops.isEmpty()) {
            for (int i = 0; i < drops.size(); i++) {
                if(p.getInventory().firstEmpty() != -1) {
                    ItemStack item = drops.get(i);
                    if(item == null) return;
                    assert item.getItemMeta() != null;
                    if(!item.getItemMeta().getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                        CustomItem.toSkyblockItem(item);
                    }
                    p.getInventory().addItem(item);
                    ItemHelper.usePress(p, item);
                    drops.set(i, new ItemStack(Material.AIR));
                } else {
                    for (ItemStack left : drops) {
                        if(left.getType() != Material.AIR) p.getWorld().dropItemNaturally(en.getLocation(), left);
                        drops.remove(left);
                    }
                    break;
                }
            }
        }
        List<PlayerContainer> players = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
        PlayerContainer pc = players.get(players.size()-1);

        if(en.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)) {
            Set<NamespacedKey> keys = en.getPersistentDataContainer().getKeys();
            // removing all keys except for one we need
            keys.remove(SkyblockD.getKey("FISHED"));
            keys.remove(SkyblockD.getKey("skyblockNative"));
            keys.remove(SkyblockD.getKey("entityLevel"));
            keys.remove(SkyblockD.getKey("entityName"));
            List<NamespacedKey> tkeys = new ArrayList<>(keys);
            NamespacedKey fishingEntity = tkeys.get(tkeys.size()-1);
            String tkey = fishingEntity.getKey().replace("skyblockd:", "");

            SeaCreatureContainer scc = SkyblockD.getServerData().fishingData.mobs.skyblock.get(tkey.toUpperCase());
            int xp = scc.exp;

            SkillContainer combat = pc.skills.data.get("fishing");
            int lvl = combat.currentLevel;
            int tlvl = lvl == 0 ? 1 : lvl;
            float modifier = SkillHelper.getModifier(tlvl);

            float exp = (xp+5)*modifier;

            operateSkill("fishing", (int) exp, p, pc);

            return;
        }

        SkillContainer combat = pc.skills.data.get("combat");
        int lvl = combat.currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;
        float modifier = SkillHelper.getModifier(tlvl);

        int baseExp = MaterialHelper.matchCombat(en.getType());
        float exp = modifier * baseExp;

        int fullExp = (int) ((int) Math.round(Objects.requireNonNull(en.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 6) + exp);

        operateSkill("combat", (int)exp, p, pc);
    }

    private void operateSkill(String name, int xp, Player p, PlayerContainer pc) {
        SkillContainer combat = pc.skills.data.get(name);
        int lvl = combat.currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;
        float modifier = SkillHelper.getModifier(tlvl);

        String sxp = String.valueOf(xp).replace(",", ".");

        PacketUtils.sendActionbar(p, "+"+sxp+" "+CustomItem.capitalize(name)+" Experience", NMSColor.DARK_AQUA);

        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);

        pc.skills.totalExp += xp;

        combat.totalExp += xp;
        combat.levelExp += xp;
        int toNext = SkyblockD.getMapManager().getMaps().get(name).getExperience().table
                .get(combat.currentLevel + 1);
        int div = combat.levelExp - toNext;
        if(div >= 0) {
            combat.levelExp = div;
            p.sendMessage(new String[]{
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------",
                            ChatColor.YELLOW + "" + ChatColor.BOLD + name.toUpperCase(Locale.ENGLISH)+" LEVEL UP!",
                            " ",
                            ChatColor.GREEN + "You are now "+name+" level " + (combat.currentLevel + 1) + "!",
                            ChatColor.GREEN + "Check out new level rewards in Skyblock Menu!",
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------"
                    }
            );
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
            int level = combat.currentLevel;
            combat.currentLevel++;
            String levl = "unlocked."+level;
            combat.collectedRewards.put(levl, true);
            ContainerHelper.updatePlayers();
            setPlayer(pc, p);
        }
    }

    private void setPlayer(PlayerContainer p, Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }
}
