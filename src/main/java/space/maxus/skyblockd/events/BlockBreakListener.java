package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.events.SkyblockBlockBreakEvent;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class BlockBreakListener extends BetterListener {
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        Material blockMat = b.getType();
        SkyblockBlockBreakEvent ev = new SkyblockBlockBreakEvent(e);
        SkyblockD.getPluginManager().callEvent(ev);
        boolean isStone = MaterialHelper.isMaterialStone(blockMat);
        boolean isWood = MaterialHelper.isMaterialLog(blockMat);
        boolean isDirt = MaterialHelper.isMaterialDirt(blockMat);
        boolean isPlant = MaterialHelper.isMaterialPlant(blockMat);
        if(isStone) {
            operateSkill("Mining", p, blockMat);
        } else if(isWood) {
            operateSkill("Foraging", p, blockMat);
        } else if(isDirt) {
            operateSkill("Excavating", p, blockMat);
        } else if(isPlant) {
            operateSkill("Farming", p, blockMat);
        }
    }

    private PlayerContainer getPlayer(Player p){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(p.getUniqueId()));
        if(conts.isEmpty()){
            PlayerContainer epc = new PlayerContainer(new RankContainer(p.getUniqueId().toString(), p.getName()), p.getUniqueId(), PlayerSkills.EMPTY, p.hasPermission("skyblockd.admin"));
            SkyblockD.players.add(epc);
            ContainerHelper.updatePlayers();
            return epc;
        }
        return conts.get(conts.size()-1);
    }

    private void setPlayer(PlayerContainer p, Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }

    private void operateSkill(String name, Player p, Material blockMat){
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        PlayerContainer cont = getPlayer(p);
        int lvl = cont.skills.data.get(name.toLowerCase(Locale.ENGLISH)).currentLevel;
        Random r = new Random();
        int rand = r.nextInt(30);
        if(rand <= lvl) {
            Item dropped = (Item) Objects.requireNonNull(p.getLocation().getWorld()).spawnEntity(p.getLocation(), EntityType.DROPPED_ITEM);
            dropped.setItemStack(new ItemStack(blockMat == Material.STONE ? Material.COBBLESTONE : blockMat, r.nextInt(2)+1));
        }
        int tlvl = lvl == 0 ? 1 : lvl;
        float exp = SkillHelper.getExpForSkill(blockMat, name.toLowerCase(Locale.ENGLISH)) * SkillHelper.getModifier(tlvl);
        String sxp = String.valueOf(exp).replace(",", ".");
        String rawCommand = "title "+p.getName()+" actionbar {\"text\":\"+"+sxp+" "+name+" Experience\", \"color\":\"dark_aqua\"}";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rawCommand);
        giveExperience(exp, name, getPlayer(p), SkyblockD.getMapManager().getMaps().get(name.toLowerCase(Locale.ENGLISH)), p);
    }

    private void giveExperience(float exp, String skill, PlayerContainer cont, SkillMap map, Player p){
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
}
