package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;

/**
 *
 *
 */
public interface ICommand {
    String getName();
    CommandExecutor getExecutor();
}
