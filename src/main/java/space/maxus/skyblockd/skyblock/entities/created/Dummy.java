package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class Dummy extends SkyblockEntity {
    @Override
    public Location getLocation(Entity p) {
        return p.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        return base;
    }

    @Override
    public EntityType getType() {
        return EntityType.IRON_GOLEM;
    }

    @Override
    public String getName() {
        return ChatColor.RED+"Training Dummy";
    }

    @Override
    public double getHealth() {
        return 1024;
    }

    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public double getDefense() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 12345;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("training_dummy");
    }

    @Override
    public void postInit(LivingEntity entity, Entity p) {
        entity.setAI(false);
        entity.setInvulnerable(true);
        p.sendMessage(ChatColor.GOLD+"Successfully generated training dummy!");
    }
}
