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

public class KingSalmon extends SkyblockEntity {
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
        return EntityType.SALMON;
    }

    @Override
    public String getName() {
        return ChatColor.GOLD+"King Salmon";
    }

    @Override
    public double getHealth() {
        return 950;
    }

    @Override
    public double getDamage() {
        return 30;
    }

    @Override
    public double getDefense() {
        return 30;
    }

    @Override
    public int getLevel() {
        return 700;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("king_salmon");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("KING_SALMON"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}