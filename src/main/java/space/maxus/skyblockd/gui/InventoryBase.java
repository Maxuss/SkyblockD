package space.maxus.skyblockd.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class InventoryBase {
    public abstract String getName();

    public abstract int getSize();

    public abstract Inventory generateContains(Inventory base);

    public abstract String getId();

    public InventoryHolder getHolder(Player p) {
        return null;
    }
}
