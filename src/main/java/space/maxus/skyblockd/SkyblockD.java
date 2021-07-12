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
import space.maxus.skyblockd.commands.*;
import space.maxus.skyblockd.events.*;
import space.maxus.skyblockd.helpers.FileHelper;
import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.items.ItemManager;
import space.maxus.skyblockd.items.TestItem;
import space.maxus.skyblockd.objects.RankContainer;
import space.maxus.skyblockd.recipes.TestRecipe;
import space.maxus.skyblockd.skyblock.events.handlers.SkyblockClickListener;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockItemRegisterer;
import space.maxus.skyblockd.skyblock.items.created.*;
import space.maxus.skyblockd.skyblock.skills.SimpleSkillMap;
import space.maxus.skyblockd.skyblock.skills.SkillResource;
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
    public static List<RankContainer> playerRanks = new ArrayList<>();

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
    public static List<String> getAllowedIngredients() {return allowedSbIngredients;}

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
        new TestRecipe();
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
        commandManager.register();

        // register items
        if (config.inDevMode()) {
            itemManager.addContain(new TestItem());
        }
        itemManager.addContain(new SkyblockMenuItem());
        itemManager.register();
        citems = itemManager.generated;

        itemRegisterer = new SkyblockItemRegisterer();

        ShadowFractureHelmet sfh = new ShadowFractureHelmet();
        ShadowFractureChestplate sfc = new ShadowFractureChestplate();
        ShadowFractureLeggings sfl = new ShadowFractureLeggings();
        ShadowFractureBoots sfb = new ShadowFractureBoots();

        citems.put(sfh.getSkyblockId(), sfh.generate());
        citems.put(sfc.getSkyblockId(), sfc.generate());
        citems.put(sfl.getSkyblockId(), sfl.generate());
        citems.put(sfb.getSkyblockId(), sfb.generate());
    }

    public void registerEvents(){
        // normal events
        new ChatListener();
        new InventoryListener();
        new LoginListener();
        new DamageListener();
        new ActionListener();
        new ClickListener();

        // skyblock related events
        new SkyblockClickListener();
        new PickupListener();
        new EntityListener();
        new CraftListener();
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
    }

    public void processRanks(){
        if (config.ranksEnabled()) {
            try {
                rankGroups = RankHelper.getGroups();
            } catch (IOException e) {
                logger.severe("Could not load rank groups!");
                logger.severe("Error: "+ Arrays.toString(e.getStackTrace()));
            }
            try {
                @SuppressWarnings("unchecked")
                JsonHelper<List<RankContainer>> container = new JsonHelper<>((Class<List<RankContainer>>) playerRanks.getClass(), true);
                Type t = new TypeToken<List<RankContainer>>() {}.getType();
                playerRanks = container.deserializeJson(JsonHelper.readJsonFile(getDataFolder().toPath() + "\\ranks.json"), t);
                if(playerRanks == null) playerRanks = new ArrayList<>();
                RankHelper.updateRanks();
            } catch (Exception e) {
                logger.severe("Could not read rank groups!");
                logger.severe("Error: "+ Arrays.toString(e.getStackTrace()));
                RankHelper.updateRanks();
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
        }

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
