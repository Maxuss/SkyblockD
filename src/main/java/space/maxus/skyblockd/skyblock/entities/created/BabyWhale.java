package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class BabyWhale extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DOLPHIN;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.GREEN+"Baby Whale";
    }

    @Override
    public double getHealth() {
        return 15;
    }

    @Override
    public double getDamage() {
        return 1;
    }

    @Override
    public double getDefense() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 800;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("baby_whale");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("BABY_WHALE"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
