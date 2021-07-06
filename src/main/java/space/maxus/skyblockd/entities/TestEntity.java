package space.maxus.skyblockd.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;

public class TestEntity extends ExpandedEntity {

    @Override
    public Location getPosition(Player p) {
        return p.getLocation();
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public void initializationLogic(Entity e) {
        e.setCustomName(ChatColor.RED+"Sus Imposter!");
        e.setCustomNameVisible(true);
    }
}
