package space.maxus.skyblockd.listeners;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Objects;
import java.util.Random;

public class DamageListener extends BetterListener {
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity en = e.getEntity();

        if(en.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE) && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e;
            Entity damager = ev.getDamager();
            if(damager instanceof Player) {
                PlayerInventory inv = ((Player) damager).getInventory();
                ItemStack mainHand = inv.getItemInMainHand();
                if(!mainHand.getType().isAir() && mainHand.isSimilar(SkyblockMaterial.PRISMARINE_DAGGER.getItem())) {
                    e.setDamage(e.getDamage()*2);
                }
            }
        }

        if(en.hasMetadata("NPC")) {
            LivingEntity le = (LivingEntity) en;
            NPC npc = SkyblockD.getNpcRegistry().getNPC(le);
            int remaining = (int) (le.getHealth()-e.getFinalDamage());
            int trueRem = Math.max(remaining, 0);
            int lvl = (int) (Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 2);
            String n = le.getPersistentDataContainer().get(SkyblockD.getKey("entityName"), PersistentDataType.STRING);
            String name = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " +lvl + ChatColor.DARK_GRAY + "]" + " "
                    + n + ChatColor.RESET + " " + ChatColor.GREEN + trueRem + ChatColor.WHITE
                    + "/" + ChatColor.GREEN +
                    (int) Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()
                    + ChatColor.RED + "" + SkyblockConstants.HEALTH;
            npc.setName(name);
        }
        if (!(en instanceof ArmorStand)) {
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

            if(en instanceof LivingEntity) {
                LivingEntity le = (LivingEntity) en;
                PersistentDataContainer c = le.getPersistentDataContainer();

                if (!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                    SkyblockEntity.toSkyblockEntity(le);
                } else {
                    String name = c.get(SkyblockD.getKey("entityName"), PersistentDataType.STRING);
                    Integer lvl = c.get(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER);
                    int remaining = (int) (le.getHealth()-e.getFinalDamage());
                    int trueRem = Math.max(remaining, 0);
                    le.setCustomName(
                            ChatColor.DARK_GRAY + "[" + ChatColor.GRAY +"Lvl " + lvl + ChatColor.DARK_GRAY + "]" + " "
                                    + name + ChatColor.RESET + " " + ChatColor.GREEN + trueRem + ChatColor.WHITE
                                    + "/" + ChatColor.GREEN +
                                    (int) Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()
                                    + ChatColor.RED + " " + SkyblockConstants.HEALTH
                    );
                }
            }
        }
    }
}
