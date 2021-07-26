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

public class SeaElephant extends SkyblockEntity {
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
        return EntityType.RAVAGER;
    }

    @Override
    public String getName() {
        return ChatColor.GOLD+"Sea Elephant";
    }

    @Override
    public double getHealth() {
        return 800;
    }

    @Override
    public double getDamage() {
        return 50;
    }

    @Override
    public double getDefense() {
        return 25;
    }

    @Override
    public int getLevel() {
        return 400;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("sea_elephant");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("SEA_ELEPHANT"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}