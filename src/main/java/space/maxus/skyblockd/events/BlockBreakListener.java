package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

public class BlockBreakListener extends BetterListener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        Player p = e.getPlayer();
        Material blockMat = b.getType();
        SkyblockD.logger.info("MAT: "+blockMat.name());
        boolean isStone = MaterialHelper.isMaterialStone(blockMat);
        boolean isWood = MaterialHelper.isMaterialLog(blockMat);
        String name = p.getName();
        if(isStone && !isWood) {
            int exp = SkillHelper.getExpForSkill(blockMat, "mining");
            String rawCommand = "title "+name+" actionbar {\"text\":\"+"+exp+" Mining Experience\", \"color\":\"dark_aqua\"}";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rawCommand);
        } else if(isWood && !isStone){
            int exp = SkillHelper.getExpForSkill(blockMat, "foraging");
            String rawCommand = "title "+name+" actionbar {\"text\":\"+"+exp+" Foraging Experience\", \"color\":\"dark_aqua\"}";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rawCommand);
        }
    }
}
