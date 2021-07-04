package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.voidmoment.skyblockd.*;


public class HelpCommand extends CommandBase {

    @Override
    public String getName() {
        return "helpmee";
    }

    @Override
    public CommandExecutor getExecutor() {
        return (sender, command, label, args) -> {
            String n = sender.getName();
            SkyblockD.sender.sendMessage("Your name is "+n);
            return false;
        };
    }
}
