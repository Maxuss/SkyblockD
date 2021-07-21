package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class ChatCommand implements CommandExecutor, TabCompleter {
    private CommandInfo cinfo;

    public CommandInfo getCommandInfo() {
        return cinfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String info, String[] args) {
        if(!cinfo.permission().isEmpty() && !sender.hasPermission(cinfo.permission())) {
            sender.sendMessage(ChatColor.RED+"You are missing permissions to execute this command!");
            return true;
        }
        if(cinfo.playerOnly() && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"This command is only for players!");
            return true;
        }
        if(!cinfo.configReq().isEmpty() && !SkyblockD.getCfg().getBool(cinfo.configReq())) {
            sender.sendMessage("This command is disabled on your server! Change " + cinfo.configReq() + " to true in plugin configuration!");
            return true;
        }
        try {
            return execute(sender, args);
        } catch(Exception e) {
            sender.sendMessage(ChatColor.RED+"An exception occurred while performing command! " + e.getClass().getName() + " : " + e.getMessage());
            SkyblockD.logger.severe("Error occurred while processing command " + cinfo.name() + "!");
            SkyblockD.logger.severe(e+"\nTrace: "+ Arrays.toString(e.getStackTrace()) + "\nCause: " + e.getCause());
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String info, String[] args) {
        return onTab(sender, args);
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public List<String> onTab(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    public ChatCommand() {
        try {
            cinfo = this.getClass().getAnnotation(CommandInfo.class);
            PluginCommand cur = SkyblockD.getInstance().getCommand(cinfo.name());
            Objects.requireNonNull(cur, "Could not register command! Command with name " + cinfo.name() + " does not exist!");
            cur.setExecutor(this);
            cur.setTabCompleter(this);
            SkyblockD.logger.info("Successfully registered command " + cinfo.name());
        } catch(Exception e) {
            cinfo = null;
            SkyblockD.logger.severe(Arrays.toString(e.getStackTrace()));
        }
    }
}
