package space.maxus.skyblockd.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static space.maxus.skyblockd.helpers.GuiHelper.genSimpleMenuItem;

public class MainMenuGUI extends InventoryBase {

    private Player p;

    public void setPlayer(Player player) {
        p = player;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.DARK_GRAY + "SkyblockD Menu";
    }

    @Override
    public int getSize() {
        return 54;
    }

    // fixed indexation
    @Override
    public @NotNull Inventory generateContains(@NotNull Inventory base) {
        List<String> indev = new ArrayList<>();
        indev.add(ChatColor.RED + "In development!");
        List<String> zkill = Arrays.asList(ChatColor.GRAY+"Click this item to view", ChatColor.GRAY+"your skill information.");

        // menu glass
        ItemStack gls = GuiHelper.getMenuGlass();
        // Profile
        ItemStack profile = GuiHelper.getPlayerHead(p);
        ItemMeta pm = profile.getItemMeta();
        assert pm != null;
        pm.setDisplayName(ChatColor.YELLOW + "Your Skyblock profile");
        getPlayerStats(p, pm);
        pm.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"),PersistentDataType.STRING, "true");
        profile.setItemMeta(GuiHelper.setHideAllFlags(pm));
        base.addItem(profile);
        // Skills
        ItemStack skills = genSimpleMenuItem("Your Skills", Material.DIAMOND_SWORD, zkill);
        // Anvil
        ItemStack anvil = genSimpleMenuItem("Reforge Anvil", Material.ANVIL, Arrays.asList(
                ChatColor.GRAY+"Click this item to open",
                ChatColor.GRAY+"the Reforge Anvil inventory!"));
        // Recipes
        ItemStack recipes = genSimpleMenuItem("Recipe Book", Material.BOOK, Arrays.asList(
                ChatColor.GRAY+"Click this item to open",
                ChatColor.GRAY+"the Recipe Browser!"
        ));
        // Trades
        ItemStack trades = genSimpleMenuItem("Trades", Material.EMERALD, indev);
        // Events + calendar
        ItemStack eve = genSimpleMenuItem("Upcoming Events", Material.CLOCK, indev);
        // EC
        ItemStack ward = genSimpleMenuItem("Wardrobe", Material.ENDER_CHEST, Arrays.asList(
                ChatColor.GRAY+"Store your armor inside",
                ChatColor.GRAY+"Wardrobe and be able to",
                ChatColor.GRAY+"switch to it easily!"
        ));
        // Crafts
        ItemStack craft = genSimpleMenuItem("Crafting Table", Material.CRAFTING_TABLE, Arrays.asList(
                ChatColor.GRAY+"Access your crafting",
                ChatColor.GRAY+"table easily!"
        ));
        // Plugin settings
        ItemStack settings = genSimpleMenuItem("Settings", Material.NAME_TAG, indev);
        // Exit GUI
        ItemStack close = genSimpleMenuItem("Close", Material.NAME_TAG, indev);

        ItemStack[] items = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, profile, gls, gls, gls, gls,
                gls, gls, gls, skills, anvil, recipes, gls, gls, gls,
                gls, gls, gls, trades, eve, ward, gls, gls, gls,
                gls, gls, gls, gls, craft, gls, gls, gls, gls,
                settings, gls, gls, gls, gls, gls, gls, gls, close
        };

        base.setContents(items);

        return base;
    }

    @Override
    public @NotNull String getId() {
        return "sbd.menus.main";
    }


    @Override
    public InventoryHolder getHolder(Player pl) {
        return pl;
    }



    private void getPlayerStats(@NotNull Player p, @NotNull ItemMeta base){
        try {
            ItemStack[] armor = p.getInventory().getArmorContents();
            ItemStack heldItem = p.getInventory().getItemInMainHand();
            int strength = UniversalHelper.getStrength(p);
            int defense = 0;
            int health = 100;
            int speed = 100;
            int attackSpeed = 0;
            int seaCreatureChance = UniversalHelper.getSeaCreatureChance(p);
            int miningFortune = UniversalHelper.getMiningFortune(p);
            int farmingFortune = UniversalHelper.getFarmingFortune(p);
            int excavatingFortune = UniversalHelper.getExcavatingFortune(p);
            int abilityDamage = UniversalHelper.getAbilityDamage(p);

            for (ItemStack a : armor) {
                if(a == null || !a.hasItemMeta() || !Objects.requireNonNull(a.getItemMeta()).hasAttributeModifiers()) continue;
                ItemMeta m = a.getItemMeta();
                int[] data = operateStats(m, 0, 0, 0, 100, 100);
                defense = data[0];
                strength += data[1];
                attackSpeed = data[2];
                health = data[3];
                speed = data[4];
            }
            if (heldItem.hasItemMeta() && Objects.requireNonNull(heldItem.getItemMeta()).hasAttributeModifiers()) {
                ItemMeta m = heldItem.getItemMeta();
                int[] data2 = operateStats(m, defense, strength, attackSpeed, health, speed);
                defense = data2[0];
                strength = data2[1];
                attackSpeed = data2[2];
                health = data2[3];
                speed = data2[4];
            }

            String str = ChatColor.RED + SkyblockConstants.STRENGTH + " Strength: " + Math.round(strength/2f);
            String def = ChatColor.GREEN + SkyblockConstants.DEFENCE + " Defense: " + defense;
            String hp = ChatColor.RED + SkyblockConstants.HEALTH + " Health: " + health;
            String spd = ChatColor.WHITE + SkyblockConstants.SPEED + " Speed: " + speed;
            String ats = ChatColor.YELLOW + SkyblockConstants.ATTACK_SPEED + " Bonus Attack Speed: " + attackSpeed + "%";
            String scc = ChatColor.AQUA + SkyblockConstants.SCC + " Sea Creature Chance: " + seaCreatureChance + "%";
            String mf = ChatColor.GOLD+SkyblockConstants.FORTUNE+" Mining Fortune: " + miningFortune;
            String ff = ChatColor.GOLD+SkyblockConstants.FORTUNE+" Farming Fortune: " + farmingFortune;
            String ef = ChatColor.GOLD+SkyblockConstants.FORTUNE+" Excavating Fortune: " + excavatingFortune;
            String ad = ChatColor.LIGHT_PURPLE+SkyblockConstants.ABILITY_DAMAGE+" Ability Damage: " + abilityDamage + "%";

            base.setLore(Arrays.asList(str, def, hp, spd, ats, scc, ad, mf, ff, ef, " "));
            base.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        } catch (NullPointerException e){
            p.sendMessage(ChatColor.GOLD+"Uh oh! Looks like you are not yet ready to use SkyblockD menu!");
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), p::closeInventory, 1L);
        }
    }

    private int @NotNull [] operateStats(@NotNull ItemMeta m, int defense, int strength, int attackSpeed, int health, int speed){
        int[] data = {defense, strength, attackSpeed, health, speed};
        Attribute[] atts = {Attribute.GENERIC_ARMOR, Attribute.GENERIC_ATTACK_DAMAGE, Attribute.GENERIC_ATTACK_SPEED, Attribute.GENERIC_MAX_HEALTH, Attribute.GENERIC_MOVEMENT_SPEED};
        int[] modifiers = {10, 10, 25, 10, 1000};
        for(int i = 0; i < data.length; i++){
            Attribute att = atts[i];
            int mod = modifiers[i];
            try {
                AttributeModifier modifier = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(att)).toArray()[0];
                data[i] += modifier.getAmount() * mod;
            } catch (NullPointerException ignored) { }
        }
        return data;
    }
}
