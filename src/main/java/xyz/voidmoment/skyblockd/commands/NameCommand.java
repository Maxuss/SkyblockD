package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import xyz.voidmoment.skyblockd.SkyblockD;


public class NameCommand implements ChatCommand {

    @Override
    public String getName() {
        return "myname";
    }

    @Override
    public CommandExecutor getExecutor() {
        return (sender, command, label, args) -> {
            if(sender instanceof Player)
            {
                String n = sender.getName();
                SkyblockD.getSender().sendMessage("User "+n+" executed command "+getName());
                Player player = (Player) sender;
                player.sendMessage("Your name is "+ChatColor.YELLOW+n+ChatColor.WHITE+"!");
                return true;
            }
            return false;
        };
    }
}
