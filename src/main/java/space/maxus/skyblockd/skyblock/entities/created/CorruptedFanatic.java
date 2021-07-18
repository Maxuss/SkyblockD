package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class CorruptedFanatic extends SkyblockEntity {
    @Override
    public Location getLocation(Entity p) {
        return p.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        base.setHelmet(new ItemStack(Material.MAGENTA_STAINED_GLASS));
        return base;
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMAN;
    }

    @Override
    public String getName() {
        return ChatColor.LIGHT_PURPLE+"Corrupted Fanatic";
    }

    @Override
    public double getHealth() {
        return 350;
    }

    @Override
    public double getDamage() {
        return 15;
    }

    @Override
    public double getDefense() {
        return 5;
    }

    @Override
    public int getLevel() {
        return 100;
    }

    @Override
    public String getSkyblockId() {
        return null;
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FANATIC_1"), PersistentDataType.BYTE, (byte)1);
    }
}
