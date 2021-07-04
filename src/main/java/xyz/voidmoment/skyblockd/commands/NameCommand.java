package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import xyz.voidmoment.skyblockd.*;


public class NameCommand extends CommandBase {

    @Override
    public String getName() {
        return "myname";
    }

    @Override
    public CommandExecutor getExecutor() {
        return (sender, command, label, args) -> {
            String n = sender.getName();
            SkyblockD.getSender().sendMessage("User "+n+" executed command "+getName());
            Player player = (Player) sender;
            player.sendMessage("Your name is "+ChatColor.YELLOW+n+ChatColor.WHITE+"!");
            return false;
        };
    }
}
