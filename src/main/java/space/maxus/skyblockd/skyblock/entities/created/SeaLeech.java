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

public class SeaLeech extends SkyblockEntity {
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
        return EntityType.SILVERFISH;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Sea Leech";
    }

    @Override
    public double getHealth() {
        return 40;
    }

    @Override
    public double getDamage() {
        return 10;
    }

    @Override
    public double getDefense() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 25;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("sea_leech");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("SEA_LEECH"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
