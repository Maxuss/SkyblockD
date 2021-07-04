package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class SBDHelpCommand implements ICommand {

    @Override
    public String getName() {
        return "sbdhelp";
    }

    @Override
    public CommandExecutor getExecutor() {
        return (sender, command, label, args) ->
        {
            if(sender instanceof Player) {
                String[] help = {
                        ChatColor.GOLD + "------ SkyblockD ------",
                        genStr("myname", "What is your name"),
                        genStr("sbdhelp", "Prints this message"),
                        ChatColor.GOLD + "------ SkyblockD ------"
                };
                String msg = String.join("\n", help);
                Player pl = (Player) sender;
                pl.sendMessage(msg);
                return true;
            }
            return false;
        };
    }

    private String genStr(String name, String usage){
        return ChatColor.YELLOW+"/"+name+ChatColor.WHITE+" - "+usage;
    }
}
