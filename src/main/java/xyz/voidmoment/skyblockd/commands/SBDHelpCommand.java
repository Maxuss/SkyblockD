package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SBDHelpCommand extends CommandBase{

    @Override
    public String getName() {
        return "sbdhelp";
    }

    @Override
    public CommandExecutor getExecutor() {
        return (sender, command, label, args) ->
        {
            String[] help = {
                    ChatColor.GOLD + "------ SkyblockD ------",
                    genStr("myname", "What is your name"),
                    genStr("sbdhelp", "Prints this message"),
                    ChatColor.GOLD + "------ SkyblockD ------"
            };
            String msg = String.join("\n", help);
            Player pl = (Player) sender;
            pl.sendMessage(msg);
            return false;
        };
    }

    private String genStr(String name, String usage){
        return ChatColor.YELLOW+"/"+name+ChatColor.WHITE+" - "+usage;
    }
}
