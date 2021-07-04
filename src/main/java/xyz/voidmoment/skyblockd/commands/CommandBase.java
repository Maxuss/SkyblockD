package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;

/**
 *
 *
 */
public abstract class CommandBase {
    public abstract String getName();
    public abstract CommandExecutor getExecutor();
}
