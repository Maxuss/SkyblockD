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
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.DamageIndicator;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.*;

public class DamageListener extends BetterListener {
    public static @NotNull HashMap<UUID, Double> dragonDamagers = new HashMap<>();
    public static @NotNull HashMap<UUID, Double> protectorDamagers = new HashMap<>();
    public static @NotNull HashMap<UUID, Double> witherDamagers = new HashMap<>();

    @EventHandler
    public void onProjectileHit(@NotNull ProjectileHitEvent e) {
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
    public void onDamage(@NotNull EntityDamageEvent e) {
        Entity en = e.getEntity();

        if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            operateHitByEntity(e, en);
        }
        if (!(en instanceof ArmorStand)) {
            operateDamageIndicators(e, en);
        }
    }

    @EventHandler
    public void onPlayerDamaged(@NotNull EntityDamageByEntityEvent e) {
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
                p.setVelocity(p.getVelocity().add(new Vector(0, 120, 0)));
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 0);
                p.sendMessage(ChatColor.RED+"Strong Dragon "+ChatColor.GRAY+"tossed you into air!");
            }
        } else if(name.contains("superior dragon")) {
            e.setDamage(e.getDamage()*1.8d);
        } else if(name.contains("absolute dragon")) {
            e.setDamage(e.getDamage()*2.1d);
        }
        ItemStack[] armor = p.getInventory().getArmorContents();
        if(damager.getPersistentDataContainer().has(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE)) {
            if(getReforgeFromArmor(armor, SkyblockReforge.TANGLED))
                e.setDamage(e.getDamage() / 2d);
            if(UniversalHelper.checkFullSet(SkyblockD.getArmorSets().get("set::NAUTILUS"), p))
                e.setDamage(e.getDamage() * 0.8d);
        }
        if(ItemHelper.isUndead(damager.getType()) && getReforgeFromArmor(armor, SkyblockReforge.MUTATED))
            e.setDamage(e.getDamage() / 1.5d);
        if(getReforgeFromArmor(armor, SkyblockReforge.ETHEREAL)) {
            int chance = new Random().nextInt(40);
            if(chance <= 2) {
                e.setDamage(0);
                p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(),
                        () -> p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1.5f), 3L);
                p.sendMessage(ChatColor.GRAY+"You dodged enemy attack thanks to your "+ChatColor.LIGHT_PURPLE+"Ethereal"+ChatColor.GRAY+" reforge!");
            }
        }

        if(e.getEntity().getType() == EntityType.WITHER) {
            Integer dat = damager.getPersistentDataContainer().get(SkyblockD.getKey("witherType"), PersistentDataType.INTEGER);
            assert dat != null;
            SkyblockEntity.WitherType t = SkyblockEntity.WitherType.values()[dat];

            operateWitherAbilities(t, damager, p, e, true);

            if(UniversalHelper.setHasKey(SkyblockD.getKey("wither"), PersistentDataType.BYTE, p))
                e.setDamage(e.getDamage()*0.8d);
        }

        if(p.getWorld().getEnvironment().equals(World.Environment.THE_END) && UniversalHelper.fullSetOfName("Erumdir", p)) {
            e.setDamage(e.getDamage()*0.8d);
        }
    }

    private void operateWitherAbilities(SkyblockEntity.WitherType t, Entity damager, Player p, EntityDamageByEntityEvent e, boolean playerHit) {
        Random r = new Random();
        LivingEntity le = (LivingEntity) damager;
        switch (t) {
            case NECRON:
                if (r.nextInt(7) <= 2 && playerHit) {
                    e.setDamage(e.getDamage() * 2);
                    p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
                    p.sendMessage(ChatColor.RED + "Necron" + ChatColor.GRAY + " dealt you double damage with his Withering Skills!");
                }
                if (r.nextInt(15) <= 2) {
                    e.setDamage(e.getDamage() * 1.5);
                    p.setVelocity(p.getVelocity().add(new Vector(50, 60, 50)));
                    p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 0);
                    p.sendMessage(ChatColor.RED + "Necron" + ChatColor.GRAY + " has thrown you away with his Powerful Knockdown!");
                }
                if (r.nextInt(25) <= 2) {
                    PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1);
                    PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 100, 10);
                    p.addPotionEffects(Arrays.asList(blind, slowness));
                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 5, 1);
                    p.sendMessage(ChatColor.RED + "Necron" + ChatColor.GRAY + " has frozen you to the ground using his Mallet of Fire!");
                }
                break;
            case STORM:
                int next = r.nextInt(10);
                if (next <= 1) {
                    PotionEffect lev = new PotionEffect(PotionEffectType.LEVITATION, 50, 5);
                    e.setDamage(e.getDamage() * 0.8d);
                    p.addPotionEffect(lev);
                    p.sendMessage(ChatColor.DARK_AQUA + "Storm" + ChatColor.GRAY + " has made you fly away using his Magical Aura!");
                }
                if (r.nextInt(15) <= 1) {
                    p.getWorld().createExplosion(p.getLocation(), 5);
                    p.sendMessage(ChatColor.DARK_AQUA + "Storm" + ChatColor.GRAY + " exploded you using his Magical Aura!");
                }
                if (r.nextInt(20) <= 1) {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Storm" + ChatColor.GRAY + " is summoning Wither Storm!");
                    for (int i = 0; i < 10; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () ->
                                p.getWorld().playSound(
                                        p.getLocation(),
                                        Sound.ENTITY_ARROW_HIT_PLAYER,
                                        5, 0), i + 2L);
                    }
                    for (int i = 0; i < 25; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                            WitherSkull skull = le.launchProjectile(WitherSkull.class, p.getLocation().toVector());
                            skull.setYield(1.5f);
                        }, i + 1L);
                    }
                }
                break;
            case GOLDOR:
                if (r.nextInt(25) <= 1) {
                    double max = Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                    le.setHealth(Math.min(le.getHealth() + 100, max));
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 0);
                    p.sendMessage(ChatColor.GOLD + "Goldor" + ChatColor.GRAY + " stole life from you and healed!");
                }
                if (r.nextInt(15) <= 1) {
                    PotionEffect res = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 10);
                    le.addPotionEffect(res);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 0);
                    p.sendMessage(ChatColor.GOLD + "Goldor" + ChatColor.GRAY + " became resistant to all damage for next 3 seconds!");
                }
                if (r.nextInt(3) <= 1) {
                    Fireball ball = le.launchProjectile(Fireball.class, p.getLocation().toVector());
                    ball.setYield(2f);
                    p.sendMessage(ChatColor.GOLD + "Goldor" + ChatColor.GRAY + " shot you with powerful fireball!");
                }
                break;
            case MAXOR:
                if (r.nextInt(10) <= 1) {
                    PotionEffect spd = new PotionEffect(PotionEffectType.SPEED, 200, 3);
                    le.addPotionEffect(spd);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Maxor" + ChatColor.GRAY + " is speeding up!");
                }
                if (r.nextInt(20) <= 1) {
                    for (int i = 0; i < 15; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                            Arrow arr = le.launchProjectile(Arrow.class, p.getLocation().toVector());
                            arr.setDamage(20);
                            arr.setColor(Color.fromRGB(156, 49, 232));
                            arr.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 200, 2), true);
                        }, 2 + i);
                    }
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Maxor" + ChatColor.GRAY + " is shooting you with barrage of arrows!");
                }
                if (r.nextInt(10) <= 1) {
                    PotionEffect weak = new PotionEffect(PotionEffectType.WEAKNESS, 100, 2);
                    p.addPotionEffect(weak);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Maxor" + ChatColor.GRAY + " has weakened you!");
                }
                break;
            case KASMIR:
                if (r.nextInt(15) <= 1) {
                    Bukkit.broadcastMessage(ChatColor.DARK_RED + "Kasmir" + ChatColor.GRAY + " is summoning help!");
                    for (int i = 0; i < 10; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () ->
                                p.getWorld().playSound(
                                        p.getLocation(),
                                        Sound.ENTITY_ARROW_HIT_PLAYER,
                                        5, 0), i + 2L);
                    }
                    for (int i = 0; i < 15; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                            WitherSkeleton skel = (WitherSkeleton) le.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKELETON);
                            skel.setTarget(p);
                        });
                    }
                }
                if (r.nextInt(10) <= 1) {
                    p.sendMessage(ChatColor.DARK_RED + "Kasmir" + ChatColor.GRAY + " is consuming your resistance!");
                    PotionEffect hunger = new PotionEffect(PotionEffectType.HUNGER, 200, 3);
                    p.addPotionEffect(hunger);
                }
                if (r.nextInt(20) <= 1) {
                    Bukkit.broadcastMessage(ChatColor.DARK_RED + "Kasmir" + ChatColor.GRAY + " is using his Crack The Sky!");
                    for (int i = 0; i < 10; i++) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () ->
                                p.getWorld().playSound(
                                        p.getLocation(),
                                        Sound.ENTITY_ARROW_HIT_PLAYER,
                                        5, 0), i + 2L);
                    }

                    int x = p.getLocation().getBlockX();
                    int py = p.getLocation().getBlockY();
                    int y = Math.min(py + 20, 256);
                    int z = p.getLocation().getBlockZ();
                    World w = p.getWorld();

                    for (int i = 0; i < 20; i++) {
                        int finalI = i;
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                            p.getWorld().createExplosion(new Location(w, x, y - finalI, z), 3f);
                        }, i + 3L);
                    }
                }
                break;
        }
    }

    private static boolean getReforgeFromArmor(ItemStack @NotNull [] armor, SkyblockReforge ref) {
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
    @SuppressWarnings("deprecation")
    private void operateHitByEntity(@NotNull EntityDamageEvent e, @NotNull Entity en) {
        EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e;
        Entity damager = ev.getDamager();

        if(!(damager instanceof Player)) return;
        int strength = UniversalHelper.getStrength((Player) damager);
        Player p = (Player) ev.getDamager();

        e.setDamage(e.getDamage()*(1+((strength+50)/100d)));

        PlayerInventory inv = ((Player) damager).getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        if(mainHand.getType().isAir()) return;
        if(!mainHand.hasItemMeta()) return;

        if(UniversalHelper.checkFullSet(SkyblockD.getArmorSets().get("set::UNSTABLE"), (Player)damager)) {
            damager.getWorld().strikeLightning(ev.getEntity().getLocation());
        }

        PersistentDataContainer c = Objects.requireNonNull(mainHand.getItemMeta()).getPersistentDataContainer();
        if(c.has(SkyblockD.getKey("reforged"), PersistentDataType.BYTE)) {
            operateReforges(ev, c, en, mainHand);
        }
        PlayerSkills skills = ContainerHelper.getPlayer(p).skills;
        if(c.has(SkyblockD.getKey("ASTRAEA"), PersistentDataType.BYTE)) {
            int lvl = skills.data.get("mining").currentLevel+1;
            ev.setDamage(ev.getDamage()*(1+(0.05d*lvl)));
        } else if(c.has(SkyblockD.getKey("SCYLLA"), PersistentDataType.BYTE)) {
            int lvl = skills.data.get("combat").currentLevel+1;
            ev.setDamage(ev.getDamage()*(1+(0.05d*lvl)));
        } else if(c.has(SkyblockD.getKey("VALKYRIE"), PersistentDataType.BYTE)) {
            int lvl = skills.data.get("foraging").currentLevel+1;
            ev.setDamage(ev.getDamage()*(1+(0.05d*lvl)));
        } else if(c.has(SkyblockD.getKey("HYPERION"), PersistentDataType.BYTE)) {
            int lvl = skills.data.get("mysticism").currentLevel + 1;
            ev.setDamage(ev.getDamage() * (1+(0.05d * lvl)));
        }

        if(ev.getEntity() instanceof EnderDragon) {
            ev.setDamage(ev.getDamage()/1.9d);
            double damage = ev.getDamage();
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
            UUID id = p.getUniqueId();
            if(protectorDamagers.containsKey(id)) {
                protectorDamagers.put(id, protectorDamagers.get(id)+damage);
            } else protectorDamagers.put(id, damage);
        } else if(ev.getEntity() instanceof Wither) {
            Wither w = (Wither) ev.getEntity();
            double newDmg = ev.getDamage()/5d;
            ev.setDamage(newDmg < 150 ? newDmg : 150);
            double damage = ev.getDamage();
            UUID id = p.getUniqueId();

            if(witherDamagers.containsKey(id)) {
                witherDamagers.put(id, witherDamagers.get(id)+damage);
            } else witherDamagers.put(id, damage);
            Integer t = ev.getEntity().getPersistentDataContainer().get(SkyblockD.getKey("witherType"), PersistentDataType.INTEGER);
            assert t != null;
            SkyblockEntity.WitherType type = SkyblockEntity.WitherType.values()[t];

            operateWitherAbilities(type, w, p, ev, false);

            if(w.getHealth() <= (w.getMaxHealth()*0.8)) {
                w.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2));
                Location witherLoc = w.getLocation();
                for(int i = 0; i < 30; i++) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                        w.teleport(witherLoc);
                        WitherSkull projectile = w.launchProjectile(WitherSkull.class);
                        projectile.setIsIncendiary(false);
                        projectile.setYield(0);
                        projectile.setVelocity(Vector.getRandom());
                        projectile.getPersistentDataContainer().set(SkyblockD.getKey("extraDamage"), PersistentDataType.DOUBLE, 33d);
                    }, i);
                }
            } else if(w.getHealth() <= (w.getMaxHealth()*0.5)) {
                w.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
            } else if(w.getHealth() <= (w.getMaxHealth()*0.3)) {
                w.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
                w.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 9));
                w.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 2));
                Location witherLoc = w.getLocation();
                for(int i = 0; i < 30; i++) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                        w.teleport(witherLoc);
                        WitherSkull projectile = w.launchProjectile(WitherSkull.class);
                        projectile.setIsIncendiary(false);
                        projectile.setYield(0);
                        projectile.setVelocity(Vector.getRandom());
                        projectile.getPersistentDataContainer().set(SkyblockD.getKey("extraDamage"), PersistentDataType.DOUBLE, 100d);
                    }, i);
                }
            }
        }
    }

    private void operateReforges(@NotNull EntityDamageByEntityEvent e, @NotNull PersistentDataContainer c, @NotNull Entity en, @NotNull ItemStack mainHand) {
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

    private void operateDamageIndicators(@NotNull EntityDamageEvent e, @NotNull Entity en) {
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
