package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class TestGUI extends InventoryBase {
    @Override
    public @NotNull String getName() {
        return ChatColor.DARK_GRAY + "Testing";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public @NotNull Inventory generateContains(@NotNull Inventory inv) {
        ItemStack stack = new ItemStack(Material.DIAMOND);
        stack.setAmount(14);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Sus!");
        stack.setItemMeta(meta);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, stack);
        }
        return inv;
    }

    @Override
    public @NotNull String getId() {
        return "test";
    }
}
