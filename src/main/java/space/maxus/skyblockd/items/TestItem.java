package space.maxus.skyblockd.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.util.ArrayList;
import java.util.List;

public class TestItem extends CustomItem {
    @Override
    public @NotNull Material getMaterial() {
        return Material.DIAMOND_HOE;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public @NotNull ItemMeta generateMeta(@NotNull ItemMeta empty) {
        empty.setDisplayName(ChatColor.RED + "Sussy Hoe!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Very sussy!");
        lore.add(ChatColor.GOLD + "Supreme" + ChatColor.BOLD + " sus");
        empty.setLore(lore);
        return empty;
    }

    @Override
    public @NotNull String getId() {
        return SkyblockD.getNamespace("item_test");
    }

}
