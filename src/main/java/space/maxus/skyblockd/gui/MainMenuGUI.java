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
        ItemStack skills = genSimpleMenuItem("Your Skills", Material.DIAMOND_SWORD, indev);
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
            for (ItemStack a : armor) {
                if (a != null && Objects.requireNonNull(a.getItemMeta()).hasAttributeModifiers()) {
                    ItemMeta m = a.getItemMeta();
                    try {
                        AttributeModifier def = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ARMOR)).toArray()[0];
                        defense += def.getAmount() * 10;
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        AttributeModifier str = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)).toArray()[0];
                        strength += str.getAmount() * 10;
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        AttributeModifier ats = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED)).toArray()[0];
                        attackSpeed += ats.getAmount() * 25;
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        AttributeModifier hp = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MAX_HEALTH)).toArray()[0];
                        health += hp.getAmount() * 10;
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        AttributeModifier spd = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED)).toArray()[0];
                        speed += spd.getAmount() * 1000;
                    } catch (NullPointerException ignored) {
                    }
                }
            }
            if (heldItem.hasItemMeta() && Objects.requireNonNull(heldItem.getItemMeta()).hasAttributeModifiers()) {
                ItemMeta m = heldItem.getItemMeta();
                try {
                    AttributeModifier def = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ARMOR)).toArray()[0];
                    defense += def.getAmount() * 10;
                } catch (NullPointerException ignored) {
                }
                try {
                    AttributeModifier str = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE)).toArray()[0];
                    strength += str.getAmount() * 10;
                } catch (NullPointerException ignored) {
                }
                try {
                    AttributeModifier ats = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED)).toArray()[0];
                    attackSpeed += ats.getAmount() * 10;
                } catch (NullPointerException ignored) {
                }
                try {
                    AttributeModifier hp = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MAX_HEALTH)).toArray()[0];
                    health += hp.getAmount() * 10;
                } catch (NullPointerException ignored) {
                }
                try {
                    AttributeModifier spd = (AttributeModifier) Objects.requireNonNull(m.getAttributeModifiers(Attribute.GENERIC_MOVEMENT_SPEED)).toArray()[0];
                    speed += spd.getAmount() * 1000;
                } catch (NullPointerException ignored) {
                }
            }

            String str = ChatColor.RED + SkyblockConstants.STRENGTH + " Strength: " + strength;
            String def = ChatColor.GREEN + SkyblockConstants.DEFENCE + " Defense: " + defense;
            String hp = ChatColor.RED + SkyblockConstants.HEALTH + " Health: " + health;
            String spd = ChatColor.WHITE + SkyblockConstants.SPEED + " Speed: " + speed;
            String ats = ChatColor.YELLOW + SkyblockConstants.ATTACK_SPEED + " Bonus Attack Speed: " + attackSpeed;

            base.setLore(Arrays.asList(str, def, hp, spd, ats));
        } catch (NullPointerException e){
            p.sendMessage(ChatColor.GOLD+"Uh oh! Looks like you are not yet ready to use SkyblockD menu!");
        }
    }
}
