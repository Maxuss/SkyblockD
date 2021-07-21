package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class SeaGuardian extends SkyblockEntity {
    @Override
    public Location getLocation(Entity e) {
        return e.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        return base;
    }

    @Override
    public EntityType getType() {
        return EntityType.GUARDIAN;
    }

    @Override
    public String getName() {
        return ChatColor.RED+"Sea Guardian";
    }

    @Override
    public double getHealth() {
        return 200;
    }

    @Override
    public double getDamage() {
        return 25;
    }

    @Override
    public double getDefense() {
        return 10;
    }

    @Override
    public int getLevel() {
        return 100;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("sea_guardian");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("SEA_GUARDIAN"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);

    }
}
