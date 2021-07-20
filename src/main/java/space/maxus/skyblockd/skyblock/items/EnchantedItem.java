package space.maxus.skyblockd.skyblock.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class EnchantedItem extends SimpleItem {
    public EnchantedItem(String name, Material origin, SkyblockRarity rarity) {
        super(name, rarity, origin, true);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(SkyblockD.getKey("blockClicks"), PersistentDataType.BYTE, (byte)1);
        meta.getPersistentDataContainer().set(SkyblockD.getKey("compacted"), PersistentDataType.BYTE, (byte)1);
        item.setItemMeta(meta);
    }
}
