package space.maxus.skyblockd;

import com.google.gson.reflect.TypeToken;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import space.maxus.skyblockd.commands.ChatCommand;
import space.maxus.skyblockd.discord.Discord;
import space.maxus.skyblockd.enchants.ItemGlint;
import space.maxus.skyblockd.enchants.ReplenishEnchantment;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.FileHelper;
import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.items.ItemManager;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.ServerStorage;
import space.maxus.skyblockd.recipes.RecipeRegisterer;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockItemRegisterer;
import space.maxus.skyblockd.skyblock.items.created.*;
import space.maxus.skyblockd.skyblock.skills.SimpleSkillMap;
import space.maxus.skyblockd.skyblock.skills.SkillMapManager;
import space.maxus.skyblockd.skyblock.skills.SkillResource;
import space.maxus.skyblockd.skyblock.skills.created.*;
import space.maxus.skyblockd.util.Config;
import space.maxus.skyblockd.util.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class SkyblockD extends JavaPlugin {

    private static SkyblockD instance;
    private static ItemManager itemManager;
    private static HashMap<String, Object> rankGroups;
    private static TreeMap<String, ItemStack> citems;
    private static PluginManager pluginManager;
    private static Config config;
    private static Constants consts;
    private static SkyblockItemRegisterer itemRegisterer;
    private static @NotNull HashMap<String, ArmorSet> armorSets = new HashMap<>();
    private static ServerStorage serverData;

    private static Discord discord;

    public static List<PlayerContainer> players = new ArrayList<>();
    public static boolean inMaintenace = false;

    private static final SkillMapManager mapManager = new SkillMapManager();
    private static final List<String> allowedSbIngredients = Arrays.asList(
            "Nullified Abyss", "Error?"
    );

    // version and stuff here:
    private static String shortVersion;
    private static String longVersion;
    private static String versionName;
    private static String pluginName;
    private static String fullShortName;
    private static String fullLongName;
    private static String namespacedKey;
    private static String motd;

    public static Logger logger;

    public static SkyblockD getInstance() {
        return instance;
    }
    public static HashMap<String, Object> getRankGroups() {
        return rankGroups;
    }
    public static @NotNull Server getHost() {
        return instance.getServer();
    }
    public static @NotNull ConsoleCommandSender getSender() {
        return getHost().getConsoleSender();
    }
    public static @NotNull String getServerName() {
        return getHost().getName();
    }
    public static @NotNull String getServerIp() {
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
    public static Constants getConsts() {
        return consts;
    }
    public static World getWorld() {
        return getHost().getWorlds().get(0);
    }
    public static SkyblockItemRegisterer getItemRegisterer() { return itemRegisterer; }
    public static @NotNull HashMap<String, ArmorSet> getArmorSets() {return armorSets;}
    public static @NotNull List<String> getAllowedIngredients() {return allowedSbIngredients;}
    public static @NotNull SkillMapManager getMapManager() { return mapManager; }
    public static ServerStorage getServerData() { return serverData; }
    public static List<PlayerContainer> getPlayers() { return players; }

    public static String getShortVersion() {
        return shortVersion;
    }
    public static String getLongVersion() {
        return longVersion;
    }
    public static String getVersionName() {
        return versionName;
    }
    public static String getPluginName() {
        return pluginName;
    }
    public static String getFullShortName() {
        return fullShortName;
    }
    public static String getFullLongName() {
        return fullLongName;
    }
    public static @NotNull String getNamespace() {
        return namespacedKey + ":";
    }
    public static String getMotd() { return motd; }

    public static Discord getDiscord() { return discord; }

    public static @NotNull String getNamespace(@NotNull String name) {
        return namespacedKey + ":" + name.toUpperCase(Locale.ENGLISH);
    }
    public static @NotNull NamespacedKey getKey(@NotNull String name) { return new NamespacedKey(SkyblockD.getInstance(), name); }

    private void initialize() {
        instance = this;
        itemManager = new ItemManager();
        pluginManager = getHost().getPluginManager();
        config = new Config(this, "config.yml");
        logger = getLogger();
        consts = new Constants();

    }

    private void generateFiles() {
        File ranks = new File(getDataFolder().toPath() + "\\ranks.json");
        if (!ranks.exists()) {
            try {
                boolean cr = ranks.createNewFile();
                if (!cr) throw new IOException();
            } catch (IOException e) {
                logger.severe("Could not create rank data file!");
            }
        }
    }

    private void registerRecipes(){
        RecipeRegisterer.registerEnchantedItems();
    }

    private void configureManagers() {
        // register commands
        String pkgName = getClass().getPackage().getName() + ".commands";

        for(Class<? extends ChatCommand> clazz : new Reflections(pkgName).getSubTypesOf(ChatCommand.class)) {
            try {
                clazz.getDeclaredConstructor().newInstance();
            } catch (@NotNull InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.severe("Could not initialize command " + clazz.getName() +"! " + e.getMessage() + "\n " +e.getCause() + Arrays.toString(e.getStackTrace()));
            }
        }

        // register items
        itemManager.addContain(new SkyblockMenuItem());
        itemManager.register();
        citems = itemManager.generated;

        itemRegisterer = new SkyblockItemRegisterer();

        try {
            serverData = new ServerStorage();
            motd = serverData.meta.getMotd();
            pluginName = serverData.meta.pluginName;
            versionName = serverData.meta.codename;
            longVersion = serverData.meta.fullVersion;
            shortVersion = serverData.meta.version;
            fullShortName = pluginName + " " + shortVersion;
            fullLongName = pluginName + " " + longVersion;
            namespacedKey = pluginName.toLowerCase(Locale.ENGLISH);
        } catch (IOException e) {
            SkyblockD.logger.severe(" [FATAL] > Could not read CDN data! Are you in offline mode?");
            SkyblockD.logger.severe(Arrays.toString(e.getStackTrace()));
            SkyblockD.logger.severe(e.getMessage());
            SkyblockD.logger.severe(e.getCause()+"");
        }
    }

    public void registerEvents(){
        String pkgName = getClass().getPackage().getName() + ".listeners";

        for(Class<? extends BetterListener> clazz : new Reflections(pkgName).getSubTypesOf(BetterListener.class)) {
            try {
                clazz.getDeclaredConstructor().newInstance();
            } catch (@NotNull InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.severe("Could not initialize listener! " + e);
            }
        }
    }

    public void registerEnchantments() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            logger.severe("Could not register item glint enchantment! " + Arrays.toString(e.getStackTrace()));
        }
        try {
            NamespacedKey key = new NamespacedKey(this, getDescription().getName());

            ItemGlint glow = new ItemGlint(key);
            ReplenishEnchantment replenish = new ReplenishEnchantment(getKey("replenish"));
            Enchantment.registerEnchantment(glow);
            Enchantment.registerEnchantment(replenish);
        } catch (IllegalArgumentException ignored) {
            SkyblockD.logger.info("Seems like server was reloaded! Not registering enchantments then");
        } catch (Exception e){
            logger.severe("Could not register item glint enchantment! Error: "+e.getClass()+": "+ Arrays.toString(e.getStackTrace()));
        }
    }

    public void registerArmor(){
        armorSets = new HashMap<>();
        armorSets.put("set::SHADOW_FRACTURE", new ShadowFractureSet());
        armorSets.put("set::EMERALD", new EmeraldSet());
        armorSets.put("set::LILY", new LilySet());
        armorSets.put("set::NAUTILUS", new NautilusSet());
        armorSets.put("set::KASMIR", new KasmirSet());
        armorSets.put("set::YOUNG", new YoungSet());
        armorSets.put("set::STRONG", new StrongSet());
        armorSets.put("set::SUPERIOR", new SuperiorSet());
        armorSets.put("set::UNSTABLE", new UnstableSet());
        armorSets.put("set::OLD", new OldSet());
        armorSets.put("set::TITAN", new TitanicSet());
        armorSets.put("set::NECRON", new NecronSet());
        armorSets.put("set::STORM", new StormSet());
        armorSets.put("set::GOLDOR", new GoldorSet());
        armorSets.put("set::MAXOR", new MaxorSet());
    }

    public void processRanks(){
        if (config.ranksEnabled()) {
            try {
                rankGroups = ContainerHelper.getGroups();
            } catch (IOException e) {
                logger.severe("Could not load rank groups!");
                logger.severe("Error: "+ Arrays.toString(e.getStackTrace()));
            }
            try {
                JsonHelper<List<PlayerContainer>> container = new JsonHelper<>(true);
                Type t = new TypeToken<List<PlayerContainer>>() {}.getType();
                players = container.deserializeJson(JsonHelper.readJsonFile(getDataFolder().toPath() + "\\players.json"), t);
                if(players == null) players = new ArrayList<>();
                ContainerHelper.updatePlayers();
            } catch (Exception e) {
                logger.info("Could not read players data! Not a problem, creating a file then...");
                ContainerHelper.updatePlayers();
            }
        }
    }

    @Override
    public void onEnable() {
        // instantiate main stuff
        initialize();

        // create necessary files
        generateFiles();

        SimpleSkillMap ssm = new SimpleSkillMap("Gamer", "Gaming");

        SkillResource sk = new SkillResource(ssm);

        try {
            JsonHelper<SkillResource> json = new JsonHelper<>(true);
            String j = json.serializeJson(sk);
            FileHelper.writeFile(getDataFolder().toPath()+"\\test.json", j);
            logger.info("Created file!");
        } catch (IOException e) {
            logger.info(e.toString());
        }

        ///
        configureManagers();

        registerEvents();

        registerArmor();

        registerEnchantments();

        registerRecipes();

        // initialize rank groups
        try {
            processRanks();
        } catch(Exception e){
            logger.severe("Could not process rank groups!");
            logger.info("Sometimes that happens if you reload the plugin a lot of times!");
            logger.info("Exception info: " + Arrays.toString(e.getStackTrace()));
        }

        // register maps
        mapManager.addMap("mining", new MiningSkillMap("Mining", "Speleologist"));
        mapManager.addMap("foraging", new ForagingSkillMap("Foraging", "Logger"));
        mapManager.addMap("excavating", new ExcavatingSkillMap("Excavating", "Digger"));
        mapManager.addMap("crafting", new CraftingSkillMap("Crafting", "Engineer"));
        mapManager.addMap("farming", new FarmingSkillMap("Farming", "Farmhand"));
        mapManager.addMap("mysticism", new MysticismSkillMap("Mysticism", "Wizard"));
        mapManager.addMap("combat", new CombatSkillMap("Combat", "Warrior"));
        mapManager.addMap("fishing", new FishingSkillMap("Fishing", "Angler"));

        if(config.discordEnabled()) {
            discord = new Discord();
            discord.sendMessage("Server launched! Everyone can join now!");
        }
        // send success message and log
        getSender().sendMessage(ChatColor.BOLD + "[" + ChatColor.GOLD + "SkyblockD" + ChatColor.RESET + "" + ChatColor.BOLD + "]" + ChatColor.RESET + " Plugin initialized!");
        logger.info("Successfully loaded SkyblockD plugin!");
    }

    @Override
    public void onDisable() {

        discord.sendMessage("Server is closing!");
        if(discord != null) discord.shutdown();

        // clear all recipes cus they break on /reload otherwise
        getHost().clearRecipes();
        getHost().resetRecipes();

        // de-instantiate main stuff
        // send message because of disabling
        getSender().sendMessage(ChatColor.BOLD + "[" + ChatColor.GOLD + "SkyblockD" + ChatColor.RESET + "" + ChatColor.BOLD + "]" + ChatColor.RESET + " Plugin disabled!");
        logger.info("Successfully unloaded SkyblockD plugin!");

        instance = null;
        pluginManager = null;
        itemManager = null;
        logger = null;
        config = null;
        consts = null;
    }
}
