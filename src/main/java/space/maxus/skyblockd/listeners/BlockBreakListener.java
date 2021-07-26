package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.nms.NMSColor;
import space.maxus.skyblockd.nms.PacketUtils;
import space.maxus.skyblockd.objects.*;
import space.maxus.skyblockd.skyblock.events.SkyblockBlockBreakEvent;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.*;
import java.util.stream.Collectors;

public class BlockBreakListener extends BetterListener {
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        Material blockMat = b.getType();
        if(ItemHelper.hasMagnet(p) && p.getInventory().firstEmpty() != -1){
            e.setDropItems(false);
            Collection<ItemStack> drops = b.getDrops(p.getInventory().getItemInMainHand());
            if(!drops.isEmpty()) {
                drops.forEach(CustomItem::toSkyblockItem);
                p.getInventory().addItem(drops.toArray(new ItemStack[0]));
            }
        }
        SkyblockBlockBreakEvent ev = new SkyblockBlockBreakEvent(e);
        SkyblockD.getPluginManager().callEvent(ev);
        boolean isStone = MaterialHelper.isMaterialStone(blockMat);
        boolean isWood = MaterialHelper.isMaterialLog(blockMat);
        boolean isDirt = MaterialHelper.isMaterialDirt(blockMat);
        boolean isPlant = MaterialHelper.isMaterialPlant(blockMat);
        if(isStone) {
            operateSkill("Mining", p, b, true);
        } else if(isWood) {
            operateSkill("Foraging", p, b, true);
        } else if(isDirt) {
            operateSkill("Excavating", p, b, true);
        } else if(isPlant) {
            operateSkill("Farming", p, b, true);

            ItemStack hoe = p.getInventory().getItemInMainHand();
            if(hoe.getType() == Material.AIR) return;

            PlayerInventory inv = p.getInventory();

            boolean hasBlessing = inv.contains(SkyblockMaterial.FARMHAND_BLESSING.getItem()) || inv.contains(SkyblockMaterial.FARMHAND_GLORY.getItem());

            List<ItemStack> drops = new ArrayList<>(b.getDrops(hoe));
            Ageable data = (Ageable) b.getBlockData();

            if(hasBlessing && data.getAge() < data.getMaximumAge()) {
                ItemStack first = drops.get(0);
                ItemStack second = drops.get(drops.size()-1);
                Random rand = new Random();
                ItemStack it1 = new ItemStack(first.getType(), rand.nextInt(3)+1);
                ItemStack it2 = new ItemStack(second.getType(), rand.nextInt(2)+2);

                if(ItemHelper.hasMagnet(p)) {
                    p.getInventory().addItem(it1, it2);
                } else {
                    b.getWorld().dropItemNaturally(b.getLocation(), it1);
                    b.getWorld().dropItemNaturally(b.getLocation(), it2);
                }
            }

            boolean hasSoul = inv.contains(SkyblockMaterial.FARMHAND_SOUL.getItem()) || inv.contains(SkyblockMaterial.FARMHAND_GLORY.getItem());

            if(hasSoul && !isPumpkin(b.getType())) {
                List<ItemStack> filtered = drops.stream().filter(i -> i.getType().name().contains("_SEEDS")).collect(Collectors.toList());
                ItemStack drop;
                if(filtered.isEmpty()) {
                    drop = drops.get(0);
                } else drop = filtered.get(0);
                CustomItem.toSkyblockItem(drop);
                if(p.getInventory().contains(drop.getType())) {
                    ItemStack removed = new ItemStack(drop.getType(), 1);
                    CustomItem.toSkyblockItem(removed);
                    p.getInventory().removeItem(removed);
                    Material type = b.getType();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> b.setType(type), 1L);
                }
            }
        }
    }

    public static PlayerContainer getPlayer(Player p){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(p.getUniqueId()));
        if(conts.isEmpty()){
            PlayerContainer epc = new PlayerContainer(new RankContainer(p.getUniqueId().toString(), p.getName()), p.getUniqueId(), PlayerSkills.EMPTY, p.hasPermission("skyblockd.admin"));
            SkyblockD.players.add(epc);
            ContainerHelper.updatePlayers();
            return epc;
        }
        return conts.get(conts.size()-1);
    }

    public static void setPlayer(PlayerContainer p, Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }

    public static void operateSkill(String name, Player p, Block block, boolean spawnExtra){
        if(name.toLowerCase(Locale.ENGLISH).equals("farming")) {
            BlockData data = block.getBlockData();
            Ageable age = (Ageable) data;
            if(age.getAge() < age.getMaximumAge()) {
                return;
            }
        }
        Material blockMat = block.getType();
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        PlayerContainer cont = getPlayer(p);
        int lvl = cont.skills.data.get(name.toLowerCase(Locale.ENGLISH)).currentLevel;
        if(spawnExtra) {
            Random r = new Random();
            int rand = r.nextInt(30);
            if (rand <= lvl) {
                try {
                    if (!ItemHelper.hasMagnet(p)) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(blockMat == Material.STONE ? Material.COBBLESTONE : blockMat, r.nextInt(2) + 1));
                    } else {
                        p.getInventory().addItem(new ItemStack(blockMat == Material.STONE ? Material.COBBLESTONE : blockMat, r.nextInt(2) + 1));
                    }
                } catch (IllegalArgumentException ignored) {}
            }
        }
        int tlvl = lvl == 0 ? 1 : lvl;
        float exp = SkillHelper.getExpForSkill(blockMat, name.toLowerCase(Locale.ENGLISH)) * SkillHelper.getModifier(tlvl);
        String sxp = String.valueOf(Math.round(exp));

        PacketUtils.sendActionbar(p, "+"+sxp+" "+name+" Experience", NMSColor.DARK_AQUA);

        giveExperience(exp, name, getPlayer(p), SkyblockD.getMapManager().getMaps().get(name.toLowerCase(Locale.ENGLISH)), p);
    }

    public static void giveExperience(float exp, String skill, PlayerContainer cont, SkillMap map, Player p){
        cont.skills.totalExp += exp;
        SkillContainer skc = cont.skills.data.get(skill.toLowerCase(Locale.ENGLISH));
        skc.totalExp += exp;
        skc.levelExp += exp;
        int toNext = map.getExperience().table
                .get(skc.currentLevel + 1);
        int div = skc.levelExp - toNext;
        if(div >= 0) {
            skc.levelExp = div;
            p.sendMessage(new String[]{
                    ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------",
                    ChatColor.YELLOW + "" + ChatColor.BOLD + skill.toUpperCase(Locale.ENGLISH) + " LEVEL UP!",
                    " ",
                    ChatColor.GREEN + "You are now " + skill.toLowerCase(Locale.ENGLISH) + " level " + (skc.currentLevel + 1) + "!",
                    ChatColor.GREEN + "Check out new level rewards in Skyblock Menu!",
                    ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------"
                    }
            );
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
            int level = skc.currentLevel;
            skc.currentLevel++;
            String lvl = "unlocked."+level;
            skc.collectedRewards.put(lvl, true);
            ContainerHelper.updatePlayers();
            setPlayer(cont, p);
            return;
        }
        setPlayer(cont, p);
    }

    private boolean isPumpkin(Material mat) {
        return mat == Material.PUMPKIN || mat == Material.MELON || mat == Material.BAMBOO || mat == Material.CACTUS;
    }
}