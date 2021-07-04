package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import xyz.voidmoment.skyblockd.SkyblockD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CommandManager {
    public ArrayList<CommandBase> commands;

    public void addCommand(CommandBase cmd){
        commands.add(cmd);
    }

    public void addCommands(CommandBase[] cmds)
    {
        commands.addAll(Arrays.asList(cmds));
    }

    public CommandManager(){
        commands = new ArrayList<>();
    }

    public void registerCommands(){
        if(!commands.isEmpty()) {
            for (CommandBase cmd : commands) {
                try {
                    String n = cmd.getName();
                    CommandExecutor e = cmd.getExecutor();
                    Objects.requireNonNull(SkyblockD.getInstance().getCommand(n)).setExecutor(e);
                    SkyblockD.getSender().sendMessage(ChatColor.GOLD+"Successfully registered command "+n+"!");
                }
                catch(Exception ex){
                    SkyblockD.getSender().sendMessage(ChatColor.RED+"Could not register command "+cmd.getName()+"!\nException: "+ex.getMessage());
                }
            }
        }
    }
}
