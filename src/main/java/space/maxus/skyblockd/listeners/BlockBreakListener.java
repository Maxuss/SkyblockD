package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;
import space.maxus.skyblockd.skyblock.events.SkyblockBlockBreakEvent;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
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
                    CustomItem.toSkyblockItem(it1);
                    CustomItem.toSkyblockItem(it2);
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

    public static void operateSkill(String name, Player p, Block block, boolean spawnExtra){
        if(name.toLowerCase(Locale.ENGLISH).equals("farming")) {
            BlockData data = block.getBlockData();
            Ageable age = (Ageable) data;
            if(age.getAge() < age.getMaximumAge()) {
                return;
            }
        }
        Material blockMat = block.getType();
        PlayerContainer cont = getPlayer(p);
        int lvl = cont.skills.data.get(name.toLowerCase(Locale.ENGLISH)).currentLevel;
        if(spawnExtra) {
            Random r = new Random();
            int rand = r.nextInt(30);
            int fortuneAmount = getSkillFortune(name, p);
            int blockAmount = (int) Math.round((fortuneAmount+rand)/100d);
            try {
                if (!ItemHelper.hasMagnet(p)) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(blockMat == Material.STONE ? Material.COBBLESTONE : blockMat, blockAmount));
                } else {
                    ItemStack cobble = new ItemStack(blockMat == Material.STONE ? Material.COBBLESTONE : blockMat, blockAmount);
                    CustomItem.toSkyblockItem(cobble);
                    p.getInventory().addItem(cobble);
                }
            } catch (IllegalArgumentException ignored) {}
        }
        int tlvl = lvl == 0 ? 1 : lvl;
        float exp = SkillHelper.getExpForSkill(blockMat, name.toLowerCase(Locale.ENGLISH)) * SkillHelper.getModifier(tlvl);

        UniversalHelper.giveSkillExperience(p, name, Math.round(exp));
    }

    private static int getSkillFortune(String name, Player p) {
        switch(name.toLowerCase(Locale.ENGLISH)) {
            case "farming":
                return UniversalHelper.getFarmingFortune(p);
            case "mining":
                return UniversalHelper.getMiningFortune(p);
            case "excavating":
                return UniversalHelper.getExcavatingFortune(p);
            default:
                return 0;
        }
    }

    private boolean isPumpkin(Material mat) {
        return mat == Material.PUMPKIN || mat == Material.MELON || mat == Material.BAMBOO || mat == Material.CACTUS;
    }
}
