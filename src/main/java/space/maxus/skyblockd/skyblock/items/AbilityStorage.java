package space.maxus.skyblockd.skyblock.items;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.MaterialHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.listeners.BlockBreakListener;

import java.util.*;

// a class that stores all item abilities inside itself
public class AbilityStorage {
    public static final int JUNGLE_AXE_AMOUNT = 5;
    public static final int TREECAPITATOR_AMOUNT = 15;
    public static final int TREENIHILATOR_AMOUNT = 30;
    public static final int ETHEREAL_CRUSHER_AMOUNT = 2;
    public static final int WORLD_DIGESTER_AMOUNT = 2;

    public static final int JUNGLE_AXE_CD = 2;
    public static final int TREECAPITATOR_CD = 4;
    public static final int TREENIHILATOR_CD = 3;
    public static final int ETHEREAL_CRUSHER_CD = 4;
    public static final int WORLD_DIGESTER_CD = 1;

    private static final HashSet<Material> unbreakable = new HashSet<>(Arrays.asList(
            Material.BARRIER, Material.BEDROCK, Material.END_PORTAL_FRAME,
            Material.END_PORTAL, Material.LAVA, Material.WATER,
            Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE,
            Material.EMERALD_ORE, Material.REDSTONE_ORE, Material.DIAMOND_ORE,
            Material.LAPIS_ORE, Material.OBSIDIAN
    ));

    public static void hyperionAbility(ItemStack i, @NotNull Player p) {
        if(ItemHelper.isOnCooldown(i, 0.7f, p, false)) return;
        PotionEffect absorption = new PotionEffect(PotionEffectType.ABSORPTION, 200, 1);
        Location newLoc = raycast(p, 10);
        p.teleport(newLoc);
        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
        p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1);
        p.spawnParticle(Particle.CRIT_MAGIC, p.getLocation(), 10, 1, 1, 1, 1);
        int totalDamage = 0;
        int totalEntities = 0;
        float dmg = ItemHelper.calcMagicDamage(p, 15);
        for(Entity e : p.getNearbyEntities(4, 4, 4)) {
            if(e instanceof LivingEntity) {
                if(!e.getPersistentDataContainer().has(SkyblockD.getKey("ENDSTONE_PROTECTOR"), PersistentDataType.BYTE)
                && !e.getType().equals(EntityType.WITHER)) {
                    LivingEntity le = (LivingEntity) e;
                    le.damage(dmg);
                    totalDamage += dmg;
                    totalEntities++;
                }
            }
        }
        if(totalEntities > 0) {
            p.sendMessage(ChatColor.GRAY+"Your Wither Impact hit " +ChatColor.RED+ totalEntities + ChatColor.GRAY + (totalEntities == 1 ? " enemy" : " enemies")+" for a total of "+ ChatColor.RED + totalDamage*5 + ChatColor.GRAY + " damage!");
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
            double hp = Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            double thp = hp/10d;
            p.setHealth(Math.min((p.getHealth() + thp), hp));
            p.addPotionEffect(absorption);
            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 2);
        }, 4L);
    }

    public static void demeterAbility(ItemStack i, @NotNull Player p) {
        if(ItemHelper.isOnCooldown(i, 5f, p, true)) return;
        double hp = Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        double thp = hp/4d;
        p.setHealth(Math.min((p.getHealth() + thp), hp));
        p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 1, 2);
        for (Entity e: p.getNearbyEntities(9, 9, 9)) {
            if(e instanceof Player) {
                Player pl = (Player) e;
                double max = Objects.requireNonNull(pl.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                pl.setHealth(Math.min((pl.getHealth() + thp), max));
            }
        }
    }

    public static void aoteAbility(@NotNull Player p, ItemStack i) {
        if(!ItemHelper.isOnCooldown(i, 0.5f, p, false)) {
            Location n = raycast(p, 6);
            p.teleport(n);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.3f, 1f);
            Particle.DustOptions dust = new Particle.DustOptions(
                    Color.fromRGB(158,13,255), 1
            );
            p.spawnParticle(Particle.REDSTONE, p.getLocation(), 4, 0.4, 0.4, 0.4, 0.4, dust);
        }
    }

    public static void tripleShot(@NotNull Player p) {
        Vector base = p.getEyeLocation().getDirection();
        Vector left = base.rotateAroundZ(-60d);
        Vector right = base.rotateAroundZ(60d);
        Arrow arr1 = p.launchProjectile(Arrow.class, left);
        Arrow arr2 = p.launchProjectile(Arrow.class, right);
        arr1.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
        arr2.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
    }

    public static void thanathosAbility(ItemStack i, @NotNull Player p) {
        if(!ItemHelper.isOnCooldown(i, 0.75f, p, false)) {
            Location n = raycast(p, 5);
            p.teleport(n);
            p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.3f, 1f);
            p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1, 1, 1, 1, 1);
            List<Entity> entities = p.getNearbyEntities(3, 3, 3);
            int damage = ItemHelper.calcMagicDamage(p, 5f);
            int totalDamage = 0;
            int totalEntities = 0;
            if (!entities.isEmpty()) {
                for (Entity en : entities) {
                    if (en instanceof LivingEntity) {
                        LivingEntity le = (LivingEntity) en;
                        le.damage(damage);
                        totalDamage += damage;
                        totalEntities++;
                    }
                }
            }
            if(totalEntities > 0) {
                p.sendMessage(ChatColor.GRAY+"Your God of Death hit " +ChatColor.RED+ totalEntities + ChatColor.GRAY + (totalEntities == 1 ? " enemy" : " enemies")+" for a total of "+ ChatColor.RED + totalDamage*5 + ChatColor.GRAY + " damage!");
            }
        }
    }

    public static void dragonAspectAbility(ItemStack i, @NotNull Player p) {
        if(!ItemHelper.isOnCooldown(i, 0.7f, p, false)) {
            p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 2, 1, 1, 1, 1);
            createHelix(p);
            List<Entity> entities = p.getNearbyEntities(4, 4, 4);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            int damage = ItemHelper.calcMagicDamage(p, 7.5f);
            int totalDamage = 0;
            int entityAmount = 0;
            if (!entities.isEmpty()) {
                for (Entity en : entities) {
                    if (en instanceof LivingEntity) {
                        LivingEntity le = (LivingEntity) en;
                        le.damage(damage);
                        le.setFireTicks(40);
                        totalDamage += damage;
                        entityAmount++;
                    }
                }
            }
            if(entityAmount > 0)
                p.sendMessage(ChatColor.GRAY+"Your Erumdir's Curse hit "+ChatColor.RED+entityAmount+ChatColor.GRAY+" enemies for a total of "+ChatColor.RED+totalDamage*5+ChatColor.GRAY+" damage!");
        }
    }

    public static void crusherAbility(ItemStack i, @NotNull Player p) {
        if (!ItemHelper.isOnCooldown(i, 3, p, true)) {
            Location l = p.getLocation();
            int range = 5;
            int minX = l.getBlockX() - range / 2;
            int minY = l.getBlockY() - range / 2;
            int minZ = l.getBlockZ() - range / 2;

            World world = l.getWorld();
            assert world != null;
            for (int x = minX; x < minX + range; x++) {
                for (int y = minY; y < minY + range; y++) {
                    for (int z = minZ; z < minZ + range; z++) {
                        Block b = world.getBlockAt(x, y, z);
                        if (unbreakable.contains(b.getType())) continue;
                        b.breakNaturally();
                    }
                }
            }

            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1f, 1f);

            p.sendMessage(ChatColor.GOLD + "Used " + ChatColor.GREEN + "Block Hunger" + ChatColor.GOLD + "!");

            world.spawnParticle(Particle.EXPLOSION_HUGE, l, 3, 5, 2, 2, 2);
        }
    }

    public static void dirtBlockBreaker(@NotNull Block start, int limit, ItemStack shovel, int cooldown, @NotNull Player p) {
        blockBreaker(start, limit, shovel, cooldown, p, BreakType.DIRT);
    }

    public static void woodenBlockBreaker(@NotNull Block start, int limit, ItemStack axe, int cooldown, @NotNull Player p) {
        blockBreaker(start, limit, axe, cooldown, p, BreakType.WOOD);
    }

    public static void stoneBlockBreaker(@NotNull Block start, int limit, ItemStack pickaxe, int cooldown, @NotNull Player p) {
        blockBreaker(start, limit, pickaxe, cooldown, p, BreakType.ROCK);
    }

    private static void blockBreaker(@NotNull Block start, int limit, ItemStack axe, int cd, @NotNull Player p, @NotNull BreakType t) {
        if(ItemHelper.isOnCooldown(axe, cd, p, false)) return;
        if(start.getType() == Material.OBSIDIAN) return;
        Material targetMaterial = start.getType();

        List<Block> blocks = new ArrayList<>();
        blocks.add(start);

        for (int i = 0; i < limit; i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), () -> {
                ArrayList<Block> preClonedList = new ArrayList<>(blocks);
                for (Block block : preClonedList) {
                    blocks.remove(block);
                    Block upperBlock = block.getRelative(BlockFace.UP);
                    Block lowerBlock = block.getRelative(BlockFace.DOWN);
                    Block northBlock = block.getRelative(BlockFace.NORTH);
                    Block eastBlock = block.getRelative(BlockFace.EAST);
                    Block southBlock = block.getRelative(BlockFace.SOUTH);
                    Block westBlock = block.getRelative(BlockFace.WEST);
                    for (Block b : Arrays.asList(upperBlock, lowerBlock, northBlock, eastBlock, southBlock, westBlock)) {
                        if (b.getType() == targetMaterial) {
                            if (t.isRequired(b.getType())) {
                                b.getWorld().playSound(b.getLocation(), t.sound, 0.7f, 2f);
                                b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1f, 2f);
                                if(ItemHelper.hasMagnet(p) && p.getInventory().firstEmpty() != -1) {
                                    Collection<ItemStack> drops = b.getDrops(p.getInventory().getItemInMainHand());
                                    drops.forEach(CustomItem::toSkyblockItem);
                                    p.getInventory().addItem((drops.toArray(new ItemStack[0])));
                                    for(ItemStack drop : drops) {
                                        ItemHelper.usePress(p, drop);
                                    }
                                    drops.clear();
                                    BlockBreakListener.operateSkill(t.skillName, p, b, false);
                                    blocks.add(b);
                                    b.setType(Material.AIR);
                                }  else {
                                    block.breakNaturally(axe);
                                    BlockBreakListener.operateSkill(t.skillName, p, b, false);
                                    blocks.add(b);
                                }
                            }
                        }
                    }
                }
            }, i*2L);
        }
    }

    private static void createHelix(@NotNull Player p) {
        Location loc = p.getLocation();
        int radius = 1;
        Random r = new Random(p.getWorld().getSeed());
        float j = 0.1f;
        for(double y = 0; y <= 4; y+=(0.15+j)) {
            double x = radius * Math.cos(y) * r.nextFloat() - 0.2;
            double z = radius * Math.sin(y) * r.nextFloat() - 0.1;

            p.spawnParticle(Particle.SOUL_FIRE_FLAME, new Location(p.getWorld(), (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z)), 1, 0, 0, 0, 0);
            j += 0.05f;
        }
    }

    private enum BreakType {
        WOOD(Sound.BLOCK_WOOD_BREAK, "Foraging"),
        ROCK(Sound.BLOCK_STONE_BREAK, "Mining"),
        DIRT(Sound.BLOCK_GRAVEL_BREAK, "Excavating")
        ;
        boolean isRequired(@NotNull Material mat) {
            switch(this) {
                case WOOD: return MaterialHelper.isMaterialLog(mat);
                case ROCK: return MaterialHelper.isMaterialStone(mat);
                case DIRT: return MaterialHelper.isMaterialDirt(mat);
                default: return false;
            }
        }

        String skillName;
        Sound sound;

        BreakType(Sound snd, String skill) {
            sound = snd;
            skillName = skill;
        }
    }

    // gets the position of last non-solid block in provided distance
    private static @NotNull Location raycast(@NotNull LivingEntity from, int distance) {
        try {
            Location eyes = from.getEyeLocation();
            BlockIterator iterator = new BlockIterator(from.getLocation(), 1, distance);
            while (iterator.hasNext()) {
                Location loc = iterator.next().getLocation();
                if (loc.getBlock().getType().isSolid()) {
                    if (loc.equals(from.getLocation())) {
                        from.sendMessage(ChatColor.RED + "There are blocks in the way!");
                    }
                    loc.setPitch(eyes.getPitch());
                    loc.setYaw(eyes.getYaw());
                    loc.setY(loc.getY() + 1);
                    return loc;
                }
            }
            Location n = from.getEyeLocation().clone().add(from.getEyeLocation().getDirection().multiply(distance));
            n.setPitch(eyes.getPitch());
            n.setYaw(eyes.getYaw());
            n.setY(n.getY() + 1);
            return n;
        } catch (IllegalStateException e) {
            from.sendMessage(ChatColor.RED+"There are blocks in the way!");
            return from.getLocation();
        }
    }
}
