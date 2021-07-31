package space.maxus.skyblockd.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EntityUtils {

    /**
     * Generates a new entity on based params
     *
     * @param entityType Type of entity to be created
     * @param location   Location for entity to be spawned
     * @return Generated entity
     */
    public static @Nullable Entity generateNew(@NotNull EntityType entityType, @NotNull Location location) {
        try {

            // getting and parsing bukkit's version, so it wont work only on current one
            String version = Bukkit
                    .getServer()
                    .getClass()
                    .getPackage()
                    .getName()
                    .replace(".", ",")
                    .split(",")[3] + ".";
            // getting the class path
            String name = "org.bukkit.craftbukkit." + version + "CraftWorld";
            Class<?> craftWorldClass = Class.forName(name);

            Object craftWorldObject = craftWorldClass.cast(location.getWorld());
            assert craftWorldObject != null;

            // getting this method VVVVVV
            // https://hub.spigotmc.org/stash/projects/SPIGOT/repos/craftbukkit/browse/src/main/java/org/bukkit/craftbukkit/CraftWorld.java#896
            Method createEntityMethod = craftWorldObject.getClass().getMethod("createEntity", Location.class, Class.class);

            Object entity = createEntityMethod.invoke(craftWorldObject, location, entityType.getEntityClass());
            return (Entity) entity.getClass().getMethod("getBukkitEntity").invoke(entity);

        } catch (@NotNull ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            SkyblockD.logger.severe("An error occurred in EntityUtils! " + Arrays.toString(exception.getStackTrace()));
        }
        return null;
    }
}
