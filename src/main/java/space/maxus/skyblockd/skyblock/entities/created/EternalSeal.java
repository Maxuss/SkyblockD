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

public class EternalSeal extends SkyblockEntity {

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
        return ChatColor.GOLD+"Eternal Seal";
    }

    @Override
    public double getHealth() {
        return 900;
    }

    @Override
    public double getDamage() {
        return 50;
    }

    @Override
    public double getDefense() {
        return 30;
    }

    @Override
    public int getLevel() {
        return 350;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("eternal_seal");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("ETERNAL_SEAL"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
