package space.maxus.skyblockd.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestItem implements CustomItem {
    @Override
    public Material getMaterial() {
        return Material.DIAMOND_HOE;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public ItemMeta generateMeta(ItemMeta empty) {
        empty.setDisplayName(ChatColor.RED + "Sussy Hoe!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Very sussy!");
        lore.add(ChatColor.GOLD + "Supreme" + ChatColor.BOLD + " sus");
        empty.setLore(lore);
        return empty;
    }

    @Override
    public String getRawName() {
        return "item.test";
    }

}
