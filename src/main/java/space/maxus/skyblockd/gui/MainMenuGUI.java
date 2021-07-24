package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
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
    public String getName() {
        return ChatColor.DARK_GRAY + "SkyblockD Menu";
    }

    @Override
    public int getSize() {
        return 54;
    }

    // fixed indexation
    @Override
    public Inventory generateContains(Inventory base) {
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
        profile.setItemMeta(GuiHelper.setHideAllFlags(pm));
        base.addItem(profile);
        // Skills
        ItemStack skills = genSimpleMenuItem("Your Skills", Material.DIAMOND_SWORD, zkill);
        // Collections
        ItemStack coll = genSimpleMenuItem("Collections", Material.PAINTING, indev);
        // Recipes
        ItemStack recipes = genSimpleMenuItem("Recipe Book", Material.BOOK, indev);
        // Trades
        ItemStack trades = genSimpleMenuItem("Trades", Material.EMERALD, indev);
        // Events + calendar
        ItemStack eve = genSimpleMenuItem("Upcoming Events", Material.CLOCK, indev);
        // EC
        ItemStack ec = genSimpleMenuItem("Ender Chest", Material.ENDER_CHEST, indev);
        // Crafts
        ItemStack craft = genSimpleMenuItem("Crafting Table", Material.CRAFTING_TABLE, indev);
        // Plugin settings
        ItemStack settings = genSimpleMenuItem("Settings", Material.NAME_TAG, indev);
        // Exit GUI
        ItemStack close = genSimpleMenuItem("Close", Material.NAME_TAG, indev);

        ItemStack[] items = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, profile, gls, gls, gls, gls,
                gls, gls, gls, skills, coll, recipes, gls, gls, gls,
                gls, gls, gls, trades, eve, ec, gls, gls, gls,
                gls, gls, gls, gls, craft, gls, gls, gls, gls,
                settings, gls, gls, gls, gls, gls, gls, gls, close
        };

        base.setContents(items);

        return base;
    }

    @Override
    public String getId() {
        return "sbd.menus.main";
    }


    @Override
    public InventoryHolder getHolder(Player pl) {
        return pl;
    }



    private void getPlayerStats(Player p, ItemMeta base){
        try {
            ItemStack[] armor = p.getInventory().getArmorContents();
            ItemStack heldItem = p.getInventory().getItemInMainHand();
            int strength = 0;
            int defense = 0;
            int health = 100;
            int speed = 100;
            int attackSpeed = 0;
            int seaCreatureChance = ItemHelper.getStatFromItems(p, "scc");
            for (ItemStack a : armor) {
                if (a != null && Objects.requireNonNull(a.getItemMeta()).hasAttributeModifiers()) {
                    ItemMeta m = a.getItemMeta();
                    int[] data = operateStats(m, 0, 0, 0, 100, 100);
                    defense = data[0];
                    strength = data[1];
                    attackSpeed = data[2];
                    health = data[3];
                    speed = data[4];
                }
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

            String str = ChatColor.RED + SkyblockConstants.STRENGTH + " Strength: " + strength;
            String def = ChatColor.GREEN + SkyblockConstants.DEFENCE + " Defense: " + defense;
            String hp = ChatColor.RED + SkyblockConstants.HEALTH + " Health: " + health;
            String spd = ChatColor.WHITE + SkyblockConstants.SPEED + " Speed: " + speed;
            String ats = ChatColor.YELLOW + SkyblockConstants.ATTACK_SPEED + " Bonus Attack Speed: " + attackSpeed + "%";
            String scc = ChatColor.AQUA + SkyblockConstants.SCC + " Sea Creature Chance: " + seaCreatureChance + "%";

            base.setLore(Arrays.asList(str, def, hp, spd, ats, scc, " "));
        } catch (NullPointerException e){
            p.sendMessage(ChatColor.GOLD+"Uh oh! Looks like you are not yet ready to use SkyblockD menu!");
            p.closeInventory();
        }
    }

    private int[] operateStats(ItemMeta m, int defense, int strength, int attackSpeed, int health, int speed){
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
