package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;

/**
 *
 *
 */
public interface ChatCommand {
    String getName();
    CommandExecutor getExecutor();
}
