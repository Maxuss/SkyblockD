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

public class KingGuardian extends SkyblockEntity {

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
        return EntityType.GUARDIAN;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.GOLD+"King Guardian";
    }

    @Override
    public double getHealth() {
        return 750;
    }

    @Override
    public double getDamage() {
        return 25;
    }

    @Override
    public double getDefense() {
        return 30;
    }

    @Override
    public int getLevel() {
        return 100;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("king_guardian");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("KING_GUARDIAN"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
