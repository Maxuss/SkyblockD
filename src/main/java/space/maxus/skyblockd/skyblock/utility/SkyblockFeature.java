package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;

public interface SkyblockFeature {
    String getSkyblockId();

    default void addSkyblockTag(ItemMeta m){
        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void addSkyblockTag(Entity m){
        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void addSkyblockTag(BlockStateMeta m){
        NamespacedKey key = SkyblockD.getKey("skyblockItem");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void blockVanillaRecipes(ItemMeta m){
        NamespacedKey key = SkyblockD.getKey("restrictRecipes");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }
}
