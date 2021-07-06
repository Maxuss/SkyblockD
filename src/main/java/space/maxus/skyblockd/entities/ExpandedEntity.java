package space.maxus.skyblockd.entities;

import org.bukkit.Location;
import org.bukkit.Utility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;

import java.util.Arrays;
import java.util.Objects;

public abstract class ExpandedEntity<T extends Entity> {

    public abstract Location getPosition(Player p);
    public abstract EntityType getType();

    public abstract void initializationLogic(T e);

    // default utility constructor
    @Utility
    protected ExpandedEntity() {}

    @SuppressWarnings("unchecked")
    public ExpandedEntity(Player p){
        try {
            initializationLogic((T) Objects.requireNonNull(p.getWorld()).spawnEntity(getPosition(p), getType()));
        } catch(Exception e){
            SkyblockD.logger.severe("An error occurred while initializing an entity! " + Arrays.toString(e.getStackTrace()));
        }
    }
}
