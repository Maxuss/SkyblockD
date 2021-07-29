package space.maxus.skyblockd.listeners;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class DamageListener extends BetterListener {
    public static HashMap<UUID, Double> dragonDamagers = new HashMap<>();
    public static HashMap<UUID, Double> protectorDamagers = new HashMap<>();

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
        if(en.hasMetadata("NPC")) {
            operateNpc(e, en);
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
        strength = Math.max(strength, 100);

        e.setDamage(e.getDamage()*Math.round(strength/100d));

        PlayerInventory inv = ((Player) damager).getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        if(mainHand.getType().isAir()) return;
        if(!mainHand.hasItemMeta()) return;

        PersistentDataContainer c = Objects.requireNonNull(mainHand.getItemMeta()).getPersistentDataContainer();
        if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
            operateReforges(ev, c, en, mainHand);
        }

        if(ev.getEntity() instanceof EnderDragon) {
            double damage = ev.getDamage();
            Player p = (Player) ev.getDamager();
            UUID id = p.getUniqueId();
            if(dragonDamagers.containsKey(id)) {
                dragonDamagers.put(id, dragonDamagers.get(id)+damage);
            } else dragonDamagers.put(id, damage);
        } else if(ev.getEntity().getPersistentDataContainer().has(SkyblockD.getKey("ENDSTONE_PROTECTOR"), PersistentDataType.BYTE)) {
            double damage = ev.getDamage();
            Player p = (Player) ev.getDamager();
            UUID id = p.getUniqueId();
            if(protectorDamagers.containsKey(id)) {
                protectorDamagers.put(id, protectorDamagers.get(id)+damage);
            } else protectorDamagers.put(id, damage);
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

    private void operateNpc(EntityDamageEvent e, Entity en) {
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
