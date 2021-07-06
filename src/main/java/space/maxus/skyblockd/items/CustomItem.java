package space.maxus.skyblockd.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class CustomItem {
    public abstract Material getMaterial();

    public abstract int getCount();

    public abstract ItemMeta generateMeta(ItemMeta empty);

    public abstract String getId();
}
