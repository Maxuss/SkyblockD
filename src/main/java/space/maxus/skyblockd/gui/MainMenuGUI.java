package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        pm.setLore(indev);
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
    public InventoryHolder getHolder() {
        return p;
    }

    private ItemStack genSimpleMenuItem(String name, Material material, List<String> lore) {
        ItemStack i = new ItemStack(material);
        ItemMeta sm = i.getItemMeta();
        assert sm != null;
        sm.setDisplayName(ChatColor.YELLOW + name);
        sm.setLore(lore);
        i.setItemMeta(GuiHelper.setHideAllFlags(sm));
        return i;
    }
}
