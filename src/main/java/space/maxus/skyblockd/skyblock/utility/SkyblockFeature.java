package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

public interface SkyblockFeature {
    String getSkyblockId();

    default void addSkyblockTag(@NotNull ItemMeta m){
        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void addSkyblockTag(@NotNull Entity m){
        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void addSkyblockTag(@NotNull BlockStateMeta m){
        NamespacedKey key = SkyblockD.getKey("skyblockItem");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void blockVanillaRecipes(@NotNull ItemMeta m){
        NamespacedKey key = SkyblockD.getKey("restrictRecipes");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }

    default void blockItemPlacement(@NotNull ItemMeta m){
        NamespacedKey key = SkyblockD.getKey("restrictPlacement");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
    }
}
