package xyz.voidmoment.skyblockd;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.voidmoment.skyblockd.commands.CommandManager;
import xyz.voidmoment.skyblockd.commands.NameCommand;
import xyz.voidmoment.skyblockd.commands.SBDHelpCommand;

import java.util.logging.Logger;

public class SkyblockD extends JavaPlugin {

    private static SkyblockD instance;
    private static CommandManager commandManager;

    public static Logger logger;

    public static SkyblockD getInstance() {return instance;}
    public static CommandManager getCommandManager() {return commandManager;}
    public static Server getHost() {return instance.getServer();}
    public static ConsoleCommandSender getSender() {return getHost().getConsoleSender();}
    public static String getServerName() {return getHost().getName();}
    public static String getServerIp() {return getHost().getIp();}

    @Override
    public void onEnable() {
        // instantiate main stuff
        instance = this;
        commandManager = new CommandManager();
        logger = getLogger();

        // register commands
        commandManager.addCommand(new NameCommand());
        commandManager.addCommand(new SBDHelpCommand());

        commandManager.registerCommands();

        // send success message and log
        getSender().sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" > Plugin initialized!");
        logger.info("Successfully loaded SkyblockD plugin!");
    }

    @Override
    public void onDisable() {
        // de-instantiate main stuff
        instance = null;
        commandManager = null;

        // send message because of disabling
        getSender().sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" > Plugin disabled!");
        logger.info("Successfully unloaded SkyblockD plugin!");
        logger = null;
    }
}
