package space.maxus.skyblockd.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public interface CustomItem {
    Material getMaterial();

    int getCount();

    ItemMeta generateMeta(ItemMeta empty);

    String getRawName();
}
