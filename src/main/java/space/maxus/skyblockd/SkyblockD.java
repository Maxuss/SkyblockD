package space.maxus.skyblockd;

import com.google.gson.reflect.TypeToken;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.skyblockd.commands.*;
import space.maxus.skyblockd.events.*;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.FileHelper;
import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.items.ItemManager;
import space.maxus.skyblockd.items.TestItem;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.ServerStorage;
import space.maxus.skyblockd.recipes.RecipeRegisterer;
import space.maxus.skyblockd.recipes.created.*;
import space.maxus.skyblockd.skyblock.events.handlers.SkyblockBreakListener;
import space.maxus.skyblockd.skyblock.events.handlers.SkyblockClickListener;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockItemRegisterer;
import space.maxus.skyblockd.skyblock.items.created.EmeraldSet;
import space.maxus.skyblockd.skyblock.items.created.ShadowFractureSet;
import space.maxus.skyblockd.skyblock.items.created.SkyblockMenuItem;
import space.maxus.skyblockd.skyblock.skills.SimpleSkillMap;
import space.maxus.skyblockd.skyblock.skills.SkillMapManager;
import space.maxus.skyblockd.skyblock.skills.SkillResource;
import space.maxus.skyblockd.skyblock.skills.created.*;
import space.maxus.skyblockd.utils.Config;
import space.maxus.skyblockd.utils.Constants;
import space.maxus.skyblockd.utils.ItemGlint;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
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
    private static SkyblockItemRegisterer itemRegisterer;
    private static HashMap<String, ArmorSet> armorSets = new HashMap<>();
    private static ServerStorage serverData;
    private static NPCRegistry npcRegistry;

    public static List<PlayerContainer> players = new ArrayList<>();

    private static final SkillMapManager mapManager = new SkillMapManager();
    private static final List<String> allowedSbIngredients = Arrays.asList(
            "Nullified Abyss", "Error?"
    );

    // version and stuff here:
    private static final String shortVersion = "v0.5";
    private static final String longVersion = "V0.5 Pre-Alpha";
    private static final String versionName = "Dev-test ALPHA";
    private static final String pluginName = "SkyblockD";
    private static final String fullShortName = pluginName + " " + shortVersion;
    private static final String fullLongName = pluginName + " " + longVersion;
    private static final String namespacedKey = pluginName.toLowerCase(Locale.ENGLISH);

    public static Logger logger;

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
    public static Constants getConsts() {
        return consts;
    }
    public static World getWorld() {
        return getHost().getWorlds().get(0);
    }
    public static SkyblockItemRegisterer getItemRegisterer() { return itemRegisterer; }
    public static HashMap<String, ArmorSet> getArmorSets() {return armorSets;}
    public static List<String> getAllowedIngredients() {return allowedSbIngredients;}
    public static SkillMapManager getMapManager() { return mapManager; }
    public static ServerStorage getServerData() { return serverData; }
    public static List<PlayerContainer> getPlayers() { return players; }
    public static NPCRegistry getNpcRegistry() {return npcRegistry;}

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
    public static String getNamespace() {
        return namespacedKey + ":";
    }

    public static String getNamespace(String name) {
        return namespacedKey + ":" + name.toUpperCase(Locale.ENGLISH);
    }
    public static NamespacedKey getKey(String name) { return new NamespacedKey(SkyblockD.getInstance(), name); }

    private void initialize() {
        instance = this;
        commandManager = new CommandManager();
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
        new Infusion1Recipe();
        new Infusion2Recipe();
        new PowderRecipe();
        new GrailRecipe();
        new EmeraldHelmetRecipe();
        new EmeraldChestplateRecipe();
        new EmeraldLeggingsRecipe();
        new EmeraldBootsRecipe();

        RecipeRegisterer.registerEnchantedItems();
    }

    private void configureManagers() {
        // register commands
        commandManager.addContain(new NameCommand());
        commandManager.addContain(new SBDHelpCommand());
        if (config.ranksEnabled()) {
            commandManager.addContain(new RankCommand());
        }
        if (config.inDevMode()) {
            commandManager.addContain(new DevTestCommand());
            commandManager.addContain(new DeveloperCommand());
        }
        commandManager.addContain(new UpdateCommand());
        commandManager.addContain(new GetItemCommand());
        if (config.shoutEnabled()) {
            commandManager.addContain(new ShoutCommand());
        }
        commandManager.addContain(new SkyblockMenuCommand());
        commandManager.addContain(new RecombobulateCommand());
        commandManager.addContain(new SpawnCommand());
        commandManager.register();

        PluginCommand getitem = this.getCommand("getitem");
        PluginCommand spawn = this.getCommand("spawn");

        assert getitem != null && spawn != null;

        getitem.setTabCompleter(new GetItemCompleter());
        spawn.setTabCompleter(new SpawnCompleter());

        // register items
        if (config.inDevMode()) {
            itemManager.addContain(new TestItem());
        }
        itemManager.addContain(new SkyblockMenuItem());
        itemManager.register();
        citems = itemManager.generated;

        itemRegisterer = new SkyblockItemRegisterer();

        mapManager.addMap("mining", new MiningSkillMap("Mining", "Speleologist"));
        mapManager.addMap("foraging", new ForagingSkillMap("Foraging", "Logger"));
        mapManager.addMap("excavating", new ExcavatingSkillMap("Excavating", "Digger"));
        mapManager.addMap("crafting", new CraftingSkillMap("Crafting", "Engineer"));
        mapManager.addMap("farming", new FarmingSkillMap("Farming", "Farmhand"));
        mapManager.addMap("mysticism", new MysticismSkillMap("Mysticism", "Wizard"));
        mapManager.addMap("combat", new CombatSkillMap("Combat", "Warrior"));

        try {
            serverData = new ServerStorage();
        } catch (IOException e) {
            SkyblockD.logger.severe(" [FATAL] > Could not read CDN data! Are you in offline mode?");
        }
    }

    public void registerEvents(){
        // normal events
        new ChatListener();
        new InventoryListener();
        new LoginListener();
        new DamageListener();
        new ActionListener();
        new ClickListener();

        new PickupListener();
        new EntityListener();
        new CraftListener();
        new BlockBreakListener();
        new KillListener();
        new EnchantListener();
        new PotionListener();
        new MovementListener();
        new PlayerDeathListener();
        new ShootListener();

        new SkyblockClickListener();
        new SkyblockBreakListener();
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
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException ignored) {
            SkyblockD.logger.fine("Seems like server was reloaded! Not registering enchantments then");
        } catch (Exception e){
            logger.severe("Could not register item glint enchantment! An exception "+e.getClass()+": "+ Arrays.toString(e.getStackTrace()));
        }
    }

    public void registerArmor(){
        armorSets = new HashMap<>();
        armorSets.put("set::SHADOW_FRACTURE", new ShadowFractureSet());
        armorSets.put("set::EMERALD", new EmeraldSet());
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
                @SuppressWarnings("unchecked")
                JsonHelper<List<PlayerContainer>> container = new JsonHelper<>((Class<List<PlayerContainer>>) players.getClass(), true);
                Type t = new TypeToken<List<PlayerContainer>>() {}.getType();
                players = container.deserializeJson(JsonHelper.readJsonFile(getDataFolder().toPath() + "\\players.json"), t);
                if(players == null) players = new ArrayList<>();
                ContainerHelper.updatePlayers();
            } catch (Exception e) {
                logger.severe("Could not read player groups!");
                logger.severe("Error: "+ Arrays.toString(e.getStackTrace()));
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
            @SuppressWarnings("unchecked")
            JsonHelper<SkillResource> json = new JsonHelper<>((Class<SkillResource>) sk.getClass(), true);
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

        // TEST
        // END TEST

        // initialize rank groups
        try {
            processRanks();
        } catch(Exception e){
            logger.severe("Could not process rank groups!");
            logger.info("Sometimes that happens if you reload the plugin a lot of times!");
            logger.info("Exception info: " + Arrays.toString(e.getStackTrace()));
        }

        npcRegistry = CitizensAPI.createAnonymousNPCRegistry(new MemoryNPCDataStore());

        // dispatch a command to make sure actionbar display messsages dont spam OP's chat etc.
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule sendCommandFeedback false");

        // send success message and log
        getSender().sendMessage(ChatColor.BOLD + "[" + ChatColor.GOLD + "SkyblockD" + ChatColor.RESET + "" + ChatColor.BOLD + "]" + ChatColor.RESET + " Plugin initialized!");
        logger.info("Successfully loaded SkyblockD plugin!");
    }

    @Override
    public void onDisable() {
        // deregister all NPCs so they wont clog memory
        npcRegistry.deregisterAll();

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
