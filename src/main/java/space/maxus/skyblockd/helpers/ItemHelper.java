package space.maxus.skyblockd.helpers;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.utils.ItemGlint;

public class ItemHelper {
    public static ItemMeta applyGlint(ItemMeta in){
        NamespacedKey key = new NamespacedKey(SkyblockD.getInstance(), SkyblockD.getInstance().getDescription().getName());
        ItemGlint g = new ItemGlint(key);
        in.addEnchant(g, 1, true);
        return in;
    }
}
