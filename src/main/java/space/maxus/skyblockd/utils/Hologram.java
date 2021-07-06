package space.maxus.skyblockd.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import space.maxus.skyblockd.objects.HologramParams;

import java.util.Objects;

public abstract class Hologram {

    // default constructor to be used by extenders
    public Hologram() {
    }

    public Hologram(HologramParams params) {
        // call pre init in case it modifies stuff
        HologramParams pi = preInit(params);
        ArmorStand as = (ArmorStand) Objects.requireNonNull(pi.getLocation().getWorld()).spawnEntity(pi.getLocation(), EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(pi.getDisplayText());
        as.setCustomNameVisible(true);
        as.setVisible(false);
        // call post init
        postInit(pi, as);
    }

    public abstract HologramParams preInit(HologramParams in);

    public abstract void postInit(HologramParams in, ArmorStand holo);

}
