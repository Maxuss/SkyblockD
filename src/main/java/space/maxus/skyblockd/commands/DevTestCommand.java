package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.lang.reflect.InvocationTargetException;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        try {
            SkyblockReforge.TEST.getBase().apply(p.getInventory().getItemInMainHand());
        } catch (InvocationTargetException | IllegalAccessException e) {
            // IGNORE: VvV
            // TODO: finish this
            e.printStackTrace();
        }
        p.updateInventory();
        return true;
    }
}
