package space.maxus.skyblockd.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.*;

public class DamageListener extends BetterListener {
    public static HashMap<UUID, Double> dragonDamagers = new HashMap<>();
    public static HashMap<UUID, Double> protectorDamagers = new HashMap<>();
    public static HashMap<UUID, Double> witherDamagers = new HashMap<>();

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        Projectile pr = e.getEntity();

        if(e.getHitBlock() != null && pr.getPersistentDataContainer().has(SkyblockD.getKey("despawn"), PersistentDataType.BYTE)) {
            pr.remove();
        }

        if(e.getHitEntity() == null || !(e.getHitEntity() instanceof LivingEntity) || !(pr.getShooter() instanceof Entity)) return;

        if(pr.getPersistentDataContainer().has(SkyblockD.getKey("extraDamage"), PersistentDataType.DOUBLE)) {
            Double extraDamage = pr.getPersistentDataContainer().get(SkyblockD.getKey("extraDamage"), PersistentDataType.DOUBLE);
            assert extraDamage != null;

            LivingEntity le = (LivingEntity) e.getHitEntity();
            le.damage(extraDamage);
            EntityDamageEvent event = new EntityDamageEvent((Entity) pr.getShooter(), EntityDamageEvent.DamageCause.PROJECTILE, extraDamage);
            le.setLastDamageCause(event);
            SkyblockD.getPluginManager().callEvent(event);
        }

        if(pr.getPersistentDataContainer().has(SkyblockD.getKey("heal"), PersistentDataType.BYTE)) {
            LivingEntity le = (LivingEntity) e.getHitEntity();

            if(le instanceof Player && !le.hasMetadata("NPC")) {
                Player p = (Player) le;
                double maxHp = Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                double abstr = p.getHealth() + 2.5d;
                p.setLastDamage(0);
                p.setHealth(Math.min(abstr, maxHp));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity en = e.getEntity();

        if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            operateHitByEntity(e, en);
        }
        if (!(en instanceof ArmorStand)) {
            operateDamageIndicators(e, en);
        }
    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        Entity damager = e.getDamager();
        if(damager.getCustomName() == null) return;
        String name = Objects.requireNonNull(ChatColor.stripColor(damager.getCustomName())).toLowerCase(Locale.ENGLISH);
        if(name.contains("wise dragon")) {
            int a = new Random().nextInt(5);
            if(a < 1) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 4));
                p.sendMessage(ChatColor.RED+"Wise Dragon"+ChatColor.GRAY+" applied Wither effect to you!");
            }
        } else if(name.contains("strong dragon")) {
            e.setDamage(e.getDamage()*1.4d);
            int a = new Random().nextInt(5);
            if(a < 1) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 2));
                p.setVelocity(new Vector(0, 90, 0));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 0);
                p.sendMessage(ChatColor.RED+"Strong Dragon "+ChatColor.GRAY+"tossed you into air!");
            }
        } else if(name.contains("superior dragon")) {
            e.setDamage(e.getDamage()*1.8d);
        } else if(name.contains("absolute dragon")) {
            e.setDamage(e.getDamage()*2.1d);
        }
        ItemStack[] armor = p.getInventory().getArmorContents();
        if(damager.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)
            && getReforgeFromArmor(armor, SkyblockReforge.TANGLED))
                e.setDamage(e.getDamage() / 2d);
        if(ItemHelper.isUndead(damager.getType())
                && getReforgeFromArmor(armor, SkyblockReforge.MUTATED))
            e.setDamage(e.getDamage() / 1.5d);
        if(getReforgeFromArmor(armor, SkyblockReforge.ETHEREAL)) {
            int chance = new Random().nextInt(30);
            if(chance <= 2) {
                e.setDamage(0);
                p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(),
                        () -> p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1.5f), 3L);
                p.sendMessage(ChatColor.GRAY+"You dodged enemy attack thanks to your "+ChatColor.LIGHT_PURPLE+"Ethereal"+ChatColor.GRAY+" reforge!");
            }
        }

        if(e.getEntity().getType() == EntityType.WITHER
         && UniversalHelper.setHasKey(SkyblockD.getKey("wither"), PersistentDataType.BYTE, p)) {
            e.setDamage(e.getDamage()*0.8d);
        }
    }

    private static boolean getReforgeFromArmor(ItemStack[] armor, SkyblockReforge ref) {
        for (ItemStack item : armor) {
            if(item == null || !item.hasItemMeta() || item.getType() == Material.AIR) continue;

            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            PersistentDataContainer c = meta.getPersistentDataContainer();
            if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
                Integer reforge = c.get(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER);
                assert reforge != null;
                SkyblockReforge refr = SkyblockReforge.byIndex(reforge);
                if(refr == ref) {
                    return true;
                }
            }
        }
        return false;
    }

    private void operateHitByEntity(EntityDamageEvent e, Entity en) {
        EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e;
        Entity damager = ev.getDamager();

        if(!(damager instanceof Player)) return;
        int strength = UniversalHelper.getStrength((Player) damager);

        e.setDamage(e.getDamage()*(1+((strength+50)/100d)));

        PlayerInventory inv = ((Player) damager).getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        if(mainHand.getType().isAir()) return;
        if(!mainHand.hasItemMeta()) return;

        if(UniversalHelper.checkFullSet(Arrays.asList(SkyblockMaterial.UNSTABLE_DRAGON_BOOTS.getItem(), SkyblockMaterial.UNSTABLE_DRAGON_LEGGINGS.getItem(), SkyblockMaterial.UNSTABLE_DRAGON_CHESTPLATE.getItem(), SkyblockMaterial.UNSTABLE_DRAGON_HELMET.getItem()), (Player)damager)) {
            damager.getWorld().strikeLightning(ev.getEntity().getLocation());
        }

        PersistentDataContainer c = Objects.requireNonNull(mainHand.getItemMeta()).getPersistentDataContainer();
        if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
            operateReforges(ev, c, en, mainHand);
        }

        if(ev.getEntity() instanceof EnderDragon) {
            ev.setDamage(ev.getDamage()/1.9d);
            double damage = ev.getDamage();
            Player p = (Player) ev.getDamager();
            UUID id = p.getUniqueId();
            if(dragonDamagers.containsKey(id)) {
                dragonDamagers.put(id, dragonDamagers.get(id)+damage);
            } else dragonDamagers.put(id, damage);
            String name = Objects.requireNonNull(ChatColor.stripColor(ev.getEntity().getCustomName())).toLowerCase(Locale.ENGLISH);
            if(name.contains("unstable dragon")) {
                p.getWorld().strikeLightning(p.getLocation());
                if(new Random().nextInt(5)<1) {
                    p.getWorld().createExplosion(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 2f, false, false);
                    p.sendMessage(ChatColor.RED+"Unstable Dragon"+ChatColor.GRAY+"'s aura exploded you!");
                }
            } else if(name.contains("absolute dragon")) {
                Random r = new Random();
                if(r.nextInt(4)<1) {
                    double nx = p.getLocation().getX()+r.nextInt(10);
                    double nz = p.getLocation().getZ()+r.nextInt(10);
                    double ny = p.getLocation().getY()+5;

                    p.teleport(new Location(p.getWorld(), nx, ny, nz), PlayerTeleportEvent.TeleportCause.UNKNOWN);
                    p.sendMessage(ChatColor.LIGHT_PURPLE+"Absolute Dragon"+ChatColor.GRAY+" has teleported you with it's "+ChatColor.DARK_PURPLE+"Warped Aura");
                }
            }
        } else if(ev.getEntity().getPersistentDataContainer().has(SkyblockD.getKey("ENDSTONE_PROTECTOR"), PersistentDataType.BYTE)) {
            ev.setDamage(ev.getDamage()/2d);
            double damage = ev.getDamage();
            Player p = (Player) ev.getDamager();
            UUID id = p.getUniqueId();
            if(protectorDamagers.containsKey(id)) {
                protectorDamagers.put(id, protectorDamagers.get(id)+damage);
            } else protectorDamagers.put(id, damage);
        } else if(ev.getEntity() instanceof Wither) {
            ev.setDamage(ev.getDamage()/2.5d);
            double damage = ev.getDamage();
            Player p = (Player) ev.getDamager();
            UUID id = p.getUniqueId();
            if(witherDamagers.containsKey(id)) {
                witherDamagers.put(id, witherDamagers.get(id)+damage);
            } else witherDamagers.put(id, damage);
        }
    }

    private void operateReforges(EntityDamageByEntityEvent e, PersistentDataContainer c, Entity en, ItemStack mainHand) {
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
            e.setDamage(e.getDamage() * 1.6d);
        }
        else if(reforge == SkyblockReforge.WARPED && en.getWorld().getEnvironment() == World.Environment.THE_END) {
            e.setDamage(e.getDamage() * 1.7d);
        }
        else if(reforge == SkyblockReforge.MUTATED && ItemHelper.isUndead(en.getType())) {
            e.setDamage(e.getDamage() * 1.4d);
        }
        else if(reforge == SkyblockReforge.FEROCIOUS) {
            int chance = new Random().nextInt(8);
            if(chance <= 2) {
                Bukkit.getScheduler().runTaskLater(SkyblockD.getInstance(), () -> {
                    if(!en.isDead() && e.getDamager() instanceof Player && en instanceof LivingEntity) {
                        Player p = (Player) e.getDamager();
                        p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1, 2);
                        LivingEntity le = (LivingEntity) en;
                        le.damage(e.getDamage()/1.5d);
                        Particle.DustOptions options = new Particle.DustOptions(Color.RED, 0.8f);
                        le.getWorld().spawnParticle(Particle.REDSTONE, le.getLocation() ,10, 0.3, 0.3, 0.3, 0.3, options);
                    }
                }, 15L);
            }
        } else if(reforge == SkyblockReforge.SACRED) {
            e.setDamage(e.getDamage()*1.5d);
        } else if(reforge == SkyblockReforge.RUSTY && en instanceof LivingEntity) {
            LivingEntity le = (LivingEntity) en;
            PotionEffect eff = new PotionEffect(PotionEffectType.POISON, 200, 3, false, true, true);
            le.addPotionEffect(eff);
        }
    }

    private void operateDamageIndicators(EntityDamageEvent e, Entity en) {
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

        new DamageIndicator(dmg*5, loc);

        if(en instanceof LivingEntity) {
            LivingEntity le = (LivingEntity) en;
            PersistentDataContainer c = le.getPersistentDataContainer();

            if (!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
                SkyblockEntity.toSkyblockEntity(le);
            } else {
                String name = c.get(SkyblockD.getKey("entityName"), PersistentDataType.STRING);
                Integer lvl = c.get(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER);
                int remaining = (int) (le.getHealth()-e.getFinalDamage())*5;
                int trueRem = Math.max(remaining, 0);
                le.setCustomName(
                        ChatColor.DARK_GRAY + "[" + ChatColor.GRAY +"Lvl " + lvl + ChatColor.DARK_GRAY + "]" + " "
                                + name + ChatColor.RESET + " " + ChatColor.GREEN + trueRem + ChatColor.WHITE
                                + "/" + ChatColor.GREEN +
                                (int) Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() * 5
                                + ChatColor.RED + " " + SkyblockConstants.HEALTH
                );
            }
        }
    }
}
