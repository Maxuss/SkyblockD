package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Objects;
import java.util.Random;

public class DamageListener extends BetterListener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity en = e.getEntity();
        if (!(en instanceof Player) && !(en instanceof ArmorStand)) {
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

            new DamageIndicator(dmg, loc);

            LivingEntity le = (LivingEntity) en;
            if(!(le instanceof Item)) {
                PersistentDataContainer c = le.getPersistentDataContainer();
                if (!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)
                        || !c.has(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER)) {
                    SkyblockEntity.toSkyblockEntity(le);
                } else {
                    String name = c.get(SkyblockD.getKey("entityName"), PersistentDataType.STRING);
                    Integer lvl = c.get(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER);
                    le.setCustomName(
                            ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + lvl + ChatColor.DARK_GRAY + "]" + " "
                                    + name + ChatColor.RESET + " " + ChatColor.GREEN + (int) le.getHealth() + ChatColor.WHITE
                                    + "/" + ChatColor.GREEN +
                                    (int) Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()
                                    + ChatColor.RED + " " + SkyblockConstants.HEALTH
                    );
                }
            }
        }
    }
}
