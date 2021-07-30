package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        return true;
    }
}
