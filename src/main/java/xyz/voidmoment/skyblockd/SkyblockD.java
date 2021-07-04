package xyz.voidmoment.skyblockd;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.voidmoment.skyblockd.commands.*;
import xyz.voidmoment.skyblockd.events.ChatListener;
import xyz.voidmoment.skyblockd.gui.InventoryManager;
import xyz.voidmoment.skyblockd.gui.TestGUI;
import xyz.voidmoment.skyblockd.helpers.JsonHelper;
import xyz.voidmoment.skyblockd.helpers.RankHelper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Logger;

public class SkyblockD extends JavaPlugin {

    private static SkyblockD instance;
    private static CommandManager commandManager;
    private static InventoryManager inventoryManager;
    private static TreeMap<String, Inventory> guis;
    private static HashMap<String, Object> rankGroups;
    private static PluginManager pluginManager;

    public static Logger logger;
    public static HashMap<String, Object> playerRanks;

    public static SkyblockD getInstance() {return instance;}
    public static CommandManager getCommandManager() {return commandManager;}
    public static InventoryManager getInventoryManager() {return inventoryManager;}
    public static TreeMap<String, Inventory> getInventories() {return guis;}
    public static HashMap<String, Object> getRankGroups() {return rankGroups;}
    public static Server getHost() {return instance.getServer();}
    public static ConsoleCommandSender getSender() {return getHost().getConsoleSender();}
    public static String getServerName() {return getHost().getName();}
    public static String getServerIp() {return getHost().getIp();}
    public static PluginManager getPluginManager() {return pluginManager;}

    public static String getCurrentDir() {return System.getProperty("user.dir");}

    @Override
    public void onEnable() {
        // instantiate main stuff
        instance = this;
        commandManager = new CommandManager();
        inventoryManager = new InventoryManager();
        pluginManager = getHost().getPluginManager();
        logger = getLogger();

        // create necessary files
        File ranks = new File(getCurrentDir()+"\\ranks.json");
        if(!ranks.exists())
        {
            try {
                boolean cr = ranks.createNewFile();
                if(!cr) throw new IOException();
            } catch (IOException e) {
                logger.severe("Could not create rank data file!");
            }
        }

        // register commands
        commandManager.addContain(new NameCommand());
        commandManager.addContain(new SBDHelpCommand());
        commandManager.addContain(new RankCommand());
        commandManager.addContain(new DevTestCommand());
        commandManager.register();

        // register inventories
        inventoryManager.addContain(new TestGUI());
        inventoryManager.register();
        guis = inventoryManager.generated;

        // initialize rank groups
        try {
            rankGroups = RankHelper.getGroups();
        } catch (IOException e) {
            logger.severe("Could not load rank groups!");
        }
        try {
            playerRanks = JsonHelper.mapJson(JsonHelper.readJsonFile(getCurrentDir()+"\\ranks.json"));
            if(playerRanks.isEmpty()) playerRanks = new HashMap<>();
            RankHelper.updateRanks();
        } catch (Exception e) {
            logger.severe("Could not read rank groups!");
            playerRanks = new HashMap<>();
            RankHelper.updateRanks();
        }

        // register events
        pluginManager.registerEvents(new ChatListener(), this);

        // send success message and log
        getSender().sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" Plugin initialized!");
        logger.info("Successfully loaded SkyblockD plugin!");
    }

    @Override
    public void onDisable() {
        // de-instantiate main stuff
        instance = null;
        commandManager = null;

        // send message because of disabling
        getSender().sendMessage(ChatColor.BOLD +"["+ ChatColor.GOLD +"SkyblockD"+ChatColor.RESET+""+ChatColor.BOLD+"]"+ChatColor.RESET+" Plugin disabled!");
        logger.info("Successfully unloaded SkyblockD plugin!");
        logger = null;
    }


}
