package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.ArrayList;
import java.util.List;

public class MainMenuGUI extends InventoryBase {

    private Player p;
    private int indx = 0;

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

        ItemStack gls = GuiHelper.getMenuGlass();
        fillWith(13, gls, base);

        // Profile
        ItemStack profile = GuiHelper.getPlayerHead(p);
        ItemMeta pm = profile.getItemMeta();
        assert pm != null;
        pm.setDisplayName(ChatColor.YELLOW + "Your Skyblock profile");
        pm.setLore(indev);
        profile.setItemMeta(GuiHelper.setHideAllFlags(pm));
        base.addItem(profile);
        indx++;

        fillWith(5, gls, base);

        // Skills
        base.addItem(genSimpleMenuItem("Your Skills", Material.DIAMOND_SWORD, indev));
        // Collections
        base.addItem(genSimpleMenuItem("Collections", Material.PAINTING, indev));
        // Recipes
        base.addItem(genSimpleMenuItem("Recipe Book", Material.BOOK, indev));
        // Trades
        base.addItem(genSimpleMenuItem("Trades", Material.EMERALD, indev));
        // Events + calendar
        base.addItem(genSimpleMenuItem("Upcoming Events", Material.CLOCK, indev));
        // EC
        base.addItem(genSimpleMenuItem("Ender Chest", Material.ENDER_CHEST, indev));

        indx += 6;

        fillWith(5, gls, base);

        // Crafts
        base.addItem(genSimpleMenuItem("Crafting Table", Material.CRAFTING_TABLE, indev));

        indx++;

        fillWith(17, gls, base);

        // Plugin settings
        base.addItem(genSimpleMenuItem("Settings", Material.NAME_TAG, indev));
        // Exit GUI
        base.addItem(genSimpleMenuItem("Close", Material.NAME_TAG, indev));

        indx += 2;

        fillWith(4, gls, base);

        return base;
    }

    @Override
    public String getId() {
        return "sbd.menus.main";
    }

    public void setPlayer(Player player) {
        p = player;
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

    private Inventory fillWith(int times, ItemStack fillable, Inventory inv) {
        for (int i = 0; i < times; i++) {
            inv.setItem(indx, fillable);
            indx++;
        }
        return inv;
    }
}
