package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(name = "ping", permission = "", playerOnly = true, configReq = "")
public class PingCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        int ping = player.getPing();
        player.sendMessage("Your ping is " + ChatColor.YELLOW + ping + ChatColor.WHITE + "!");
        return true;
    }
}
