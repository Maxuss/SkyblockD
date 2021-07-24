package space.maxus.skyblockd.listeners;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Objects;
import java.util.Random;

public class DamageListener extends BetterListener {
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity en = e.getEntity();

        if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e;
            Entity damager = ev.getDamager();

            if(!(damager instanceof Player)) return;

            PlayerInventory inv = ((Player) damager).getInventory();
            ItemStack mainHand = inv.getItemInMainHand();
            if(mainHand.getType().isAir()) return;
            if(!mainHand.hasItemMeta()) return;

            PersistentDataContainer c = Objects.requireNonNull(mainHand.getItemMeta()).getPersistentDataContainer();
            if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
                Integer ref = c.get(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER);
                assert ref != null;
                SkyblockReforge reforge = SkyblockReforge.byIndex(ref);

                if (en.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)) {
                    if (!mainHand.getType().isAir() && mainHand.isSimilar(SkyblockMaterial.PRISMARINE_DAGGER.getItem())) {
                        e.setDamage(e.getDamage() * 2);
                    }
                    if (reforge == SkyblockReforge.TANGLED) {
                        e.setDamage(e.getDamage() * 2);
                    }
                }
                if (reforge == SkyblockReforge.MAGMATIC && en.getWorld().getEnvironment() == World.Environment.NETHER) {
                    e.setDamage(e.getDamage() * 1.6f);
                }
                if(reforge == SkyblockReforge.WARPED && en.getWorld().getEnvironment() == World.Environment.THE_END) {
                    e.setDamage(e.getDamage() * 1.7f);
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

    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        Entity damager = e.getDamager();

        if(damager.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)) {
            boolean isTangled = false;
            ItemStack[] armor = p.getInventory().getArmorContents();
            for (ItemStack item : armor) {
                if(item == null || !item.hasItemMeta() || item.getType() == Material.AIR) return;

                ItemMeta meta = item.getItemMeta();
                assert meta != null;
                PersistentDataContainer c = meta.getPersistentDataContainer();
                if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
                    Integer reforge = c.get(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER);
                    assert reforge != null;
                    SkyblockReforge ref = SkyblockReforge.byIndex(reforge);
                    if(ref == SkyblockReforge.TANGLED) {
                        isTangled = true;
                        break;
                    }
                }
            }
            if(isTangled) e.setDamage(e.getDamage() / 2d);
        }
    }
}
