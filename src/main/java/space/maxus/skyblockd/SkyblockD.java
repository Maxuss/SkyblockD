package space.maxus.skyblockd;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.skyblockd.commands.*;
import space.maxus.skyblockd.events.*;
import space.maxus.skyblockd.skyblock.items.SkyblockMenuItem;
import space.maxus.skyblockd.utils.Config;
import space.maxus.skyblockd.utils.Constants;
import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.items.ItemManager;
import space.maxus.skyblockd.items.TestItem;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;
import java.util.logging.Logger;

public class SkyblockD extends JavaPlugin {

    private static SkyblockD instance;
    private static CommandManager commandManager;
    private static ItemManager itemManager;
    private static HashMap<String, Object> rankGroups;
    private static TreeMap<String, ItemStack> citems;
    private static PluginManager pluginManager;
    private static Config config;
    private static Constants consts;

    // version and stuff here:
    private static final String shortVersion = "v0.5";
    private static final String longVersion = "V0.5 Pre-Alpha";
    private static final String versionName = "Dev-test ALPHA";
    private static final String pluginName = "SkyblockD";
    private static final String fullShortName = pluginName+" "+shortVersion;
    private static final String fullLongName = pluginName+" "+longVersion;
    private static final String namespacedKey = pluginName.toLowerCase(Locale.ENGLISH);


    public static Logger logger;
    public static HashMap<String, Object> playerRanks;

    public static SkyblockD getInstance() {
        return instance;
    }
    public static CommandManager getCommandManager() {
        return commandManager;
    }
    public static HashMap<String, Object> getRankGroups() {
        return rankGroups;
    }
    public static Server getHost() {
        return instance.getServer();
    }
    public static ConsoleCommandSender getSender() {
        return getHost().getConsoleSender();
    }
    public static String getServerName() {
        return getHost().getName();
    }
    public static String getServerIp() {
        return getHost().getIp();
    }
    public static PluginManager getPluginManager() {
        return pluginManager;
    }
    public static ItemManager getItemManager() {
        return itemManager;
    }
    public static TreeMap<String, ItemStack> getCustomItems() {
        return citems;
    }
    public static Config getCfg() {
        return config;
    }
    public static String getCurrentDir() {
        return System.getProperty("user.dir");
    }
    public static Constants getConsts(){
        return consts;
    }
    public static World getWorld() {return getHost().getWorlds().get(0);}

    public static String getShortVersion() {return shortVersion;}
    public static String getLongVersion() {return longVersion;}
    public static String getVersionName() {return versionName;}
    public static String getPluginName() {return pluginName;}
    public static String getFullShortName() {return fullShortName;}
    public static String getFullLongName() {return fullLongName;}
    public static String getNamespace() {return namespacedKey+":";}
    public static String getNamespace(String name) {return namespacedKey+":"+name.toUpperCase(Locale.ENGLISH);}

    @Override
    public void onEnable() {
        // instantiate main stuff
        instance = this;
        commandManager = new CommandManager();
        itemManager = new ItemManager();
        pluginManager = getHost().getPluginManager();
        config = new Config(this, "config.yml");
        logger = getLogger();
        consts = new Constants();

        // create necessary files
        File ranks = new File(getDataFolder().toPath() + "\\ranks.json");
        if (!ranks.exists()) {
            try {
                boolean cr = ranks.createNewFile();
                if (!cr) throw new IOException();
            } catch (IOException e) {
                logger.severe("Could not create rank data file!");
            }
        }

        // register commands
        commandManager.addContain(new NameCommand());
        commandManager.addContain(new SBDHelpCommand());
        if (config.ranksEnabled()) {
            commandManager.addContain(new RankCommand());
        }
        if (config.inDevMode()) {
            commandManager.addContain(new DevTestCommand());
        }
        commandManager.addContain(new UpdateCommand());
        commandManager.addContain(new GetItemCommand());
        if (config.shoutEnabled()) {
            commandManager.addContain(new ShoutCommand());
        }
        commandManager.addContain(new SkyblockMenuCommand());
        commandManager.register();

        // register items
        if (config.inDevMode()) {
            itemManager.addContain(new TestItem());
        }
        itemManager.addContain(new SkyblockMenuItem());
        itemManager.register();
        citems = itemManager.generated;

        // initialize rank groups
        if (config.ranksEnabled()) {
            try {
                rankGroups = RankHelper.getGroups();
            } catch (IOException e) {
                logger.severe("Could not load rank groups!");
            }
            try {
                playerRanks = JsonHelper.mapJson(JsonHelper.readJsonFile(getCurrentDir() + "\\ranks.json"));
                if (playerRanks.isEmpty()) playerRanks = new HashMap<>();
                RankHelper.updateRanks();
            } catch (Exception e) {
                logger.severe("Could not read rank groups!");
                playerRanks = new HashMap<>();
                RankHelper.updateRanks();
            }
        }
        // register events
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new LoginListener(), this);
        pluginManager.registerEvents(new DamageListener(), this);
        pluginManager.registerEvents(new ActionListener(), this);

        // send success message and log
        getSender().sendMessage(ChatColor.BOLD + "[" + ChatColor.GOLD + "SkyblockD" + ChatColor.RESET + "" + ChatColor.BOLD + "]" + ChatColor.RESET + " Plugin initialized!");
        logger.info("Successfully loaded SkyblockD plugin!");
    }

    @Override
    public void onDisable() {
        // de-instantiate main stuff
        // send message because of disabling
        getSender().sendMessage(ChatColor.BOLD + "[" + ChatColor.GOLD + "SkyblockD" + ChatColor.RESET + "" + ChatColor.BOLD + "]" + ChatColor.RESET + " Plugin disabled!");
        logger.info("Successfully unloaded SkyblockD plugin!");

        instance = null;
        commandManager = null;
        pluginManager = null;
        itemManager = null;
        logger = null;
        config = null;
        consts = null;
    }
}
