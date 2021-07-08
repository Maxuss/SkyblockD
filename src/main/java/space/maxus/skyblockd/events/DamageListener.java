package space.maxus.skyblockd.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;

import java.util.Random;

public class DamageListener extends BetterListener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity en = e.getEntity();
        if (!(en instanceof Player)) {
            int dmg = (int) e.getFinalDamage();
            Location loc = en.getLocation();

            Random r = new Random();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();

            double r1 = r.nextDouble();
            double r2 = r.nextDouble();
            double r3 = r.nextDouble();

            double nx = x + (r.nextBoolean() ? r1 : -r1);
            double ny = y + (r.nextBoolean() ? r2 : -r2);
            double nz = z + (r.nextBoolean() ? r3 : -r3);

            loc.setX(nx);
            loc.setY(ny);
            loc.setZ(nz);

            DamageIndicator indicator = new DamageIndicator(dmg, loc);
        }
    }
}
