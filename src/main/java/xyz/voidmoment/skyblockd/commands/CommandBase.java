package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

/**
 *
 *
 */
public abstract class CommandBase {
    public abstract String getName();
    public abstract CommandExecutor getExecutor();
    public String getPermission() {return "skyblockd.default";}
    public String getPermissionMessage() {return ChatColor.YELLOW+"Unsufficient permissions!";}
}
