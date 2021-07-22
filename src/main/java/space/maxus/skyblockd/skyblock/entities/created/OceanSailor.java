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

public class OceanSailor extends SkyblockEntity {

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
        return EntityType.DOLPHIN;
    }

    @Override
    public String getName() {
        return ChatColor.RED+"Ocean Rider";
    }

    @Override
    public double getHealth() {
        return 150;
    }

    @Override
    public double getDamage() {
        return 10;
    }

    @Override
    public double getDefense() {
        return 10;
    }

    @Override
    public int getLevel() {
        return 50;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("ocean_sailor");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("OCEAN_RIDER"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
