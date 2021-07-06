package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import space.maxus.skyblockd.objects.HologramParams;
import space.maxus.skyblockd.utils.Hologram;

public class SimpleHologram extends Hologram {

    public SimpleHologram(String text, Location loc) {
        super(new HologramParams(text, loc));
    }

    @Override
    public HologramParams preInit(HologramParams in) {
        return in;
    }

    @Override
    public void postInit(HologramParams in, ArmorStand holo) {

    }
}
