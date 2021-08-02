package space.maxus.skyblockd.skyblock.entities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public abstract class SkyblockEntity implements SkyblockFeature {

    public abstract @NotNull Location getLocation(Entity e);
    public abstract EntityEquipment getEquipment(EntityEquipment base);
    public abstract @NotNull EntityType getType();
    public abstract @NotNull String getName();
    public abstract double getHealth();
    public abstract double getDamage();
    public abstract double getDefense();
    public abstract int getLevel();
    public abstract @Nullable String getSkyblockId();
    public abstract void postInit(LivingEntity entity, Entity base);

    private static final HashSet<Biome> nether = new HashSet<Biome>() {
        {
            add(Biome.NETHER_WASTES);
            add(Biome.SOUL_SAND_VALLEY);
            add(Biome.WARPED_FOREST);
            add(Biome.CRIMSON_FOREST);
            add(Biome.BASALT_DELTAS);
        }
    };

    public @NotNull Entity generate(@NotNull Entity en){
        LivingEntity e = (LivingEntity) en.getWorld().spawnEntity(getLocation(en), getType());

        // set equipment
        getEquipment(e.getEquipment());

        // set attributes
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(getHealth());
        e.setHealth(getHealth());
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(getDamage());
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(getDefense());

        // set display name
        e.setCustomNameVisible(true);
        e.setCustomName(
                ChatColor.DARK_GRAY + "[" + ChatColor.GRAY+ "Lv " + getLevel() + ChatColor.DARK_GRAY + "]" + " "
                + getName() + ChatColor.RESET + " " + ChatColor.GREEN + getHealth()*5 + ChatColor.WHITE
                + "/" + ChatColor.GREEN + (int) getHealth()*5 + ChatColor.RED + " " + SkyblockConstants.HEALTH
        );

        addSkyblockTag(e);
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityName"), PersistentDataType.STRING, getName());
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER, getLevel());

        // finish initialization by calling postInit
        postInit(e, en);
        return e;
    }

    public static void toSkyblockEntity(@NotNull LivingEntity e){
        String name;
        if(e.getCustomName() == null){
            name = ChatColor.RED + capitalize(e.getType().toString().toLowerCase(Locale.ENGLISH).replace("_", " "));
        } else name = e.getCustomName();

        double maxHp = Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();

        boolean isEnd = e.getWorld().getEnvironment().equals(World.Environment.THE_END);
        Biome entityBiome = e.getLocation().getBlock().getBiome();

        if(nether.contains(entityBiome) && maxHp <= 20) {
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHp * 2);
            e.setHealth(maxHp * 2);
        } else if(isEnd && maxHp <= 40) {
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHp * 4);
            e.setHealth(maxHp * 4);
            maxHp = Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
        }

        if(e.getType() == EntityType.WITHER) {
            Random r = new Random();
            int it = r.nextInt(WitherType.values().length);
            WitherType type = WitherType.values()[it];
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(type.def);
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(type.hp);
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(type.def);
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.2d);
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(type.dmg);
            e.getPersistentDataContainer().set(SkyblockD.getKey("witherType"), PersistentDataType.INTEGER, it);
            e.setHealth(type.hp);
            Bukkit.broadcastMessage(type.name+" has spawned!");
        }

        if(e.getType() == EntityType.ENDER_DRAGON) {
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(800);
            Random r = new Random();
            int rand = r.nextInt(8);
            if(rand <= 1) {
                name = ChatColor.GRAY+"Protector Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(300);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(100);
            } else if(rand == 2) {
                name = ChatColor.GRAY+"Old Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1000);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(200);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(300);
            } else if(rand == 3) {
                name = ChatColor.WHITE+"Young Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(600);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(40);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(50);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.3d);
            } else if(rand == 4) {
                name = ChatColor.AQUA+"Wise Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(900);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(40);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(3000);
            } else if(rand == 5) {
                name = ChatColor.DARK_PURPLE+"Unstable Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(700);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(80);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(60);
            } else if(rand == 6) {
                name = ChatColor.RED+"Strong Dragon";
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(700);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(150);
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(100);
            } else {
                int another = r.nextInt(10);
                if(another <= 1) {
                    name = ChatColor.LIGHT_PURPLE + "Absolute Dragon";
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1024);
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(300);
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(300);
                } else {
                    name = ChatColor.GOLD + "Superior Dragon";
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(1000);
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(250);
                    Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(250);
                }
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.3d);
            }
            if(!name.equals(ChatColor.LIGHT_PURPLE + "Absolute Dragon")) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "A " + name + ChatColor.GOLD + " has spawned!");
            } else Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Rare Absolute Dragon has spawned in the End!");
        }

        if(isEnd) {
            int tx = Math.max(Math.abs(e.getLocation().getBlockX()), Math.abs(e.getLocation().getBlockZ()));
            float multiplier = tx <= 200 ? 1 : tx <= 6000 ? tx / 1000f : 6;
            int integerMultiplier = Math.round(multiplier);

            AttributeInstance damage = e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);

            if(damage != null) {
                double dmg = damage.getBaseValue();
                Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(dmg + (2 * integerMultiplier));
            }

            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHp * integerMultiplier);

            e.setHealth(maxHp * integerMultiplier);
        }

        int lvl = (int) (e.getHealth() / 2);

        e.setCustomNameVisible(true);
        e.setCustomName(
                ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " +lvl + ChatColor.DARK_GRAY + "]" + " "
                        + name + ChatColor.RESET + " " + ChatColor.GREEN + (int) e.getHealth() * 5 + ChatColor.WHITE
                        + "/" + ChatColor.GREEN +
                        (int) Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() * 5
                        + ChatColor.RED + "" + SkyblockConstants.HEALTH
        );
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER, lvl);
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityName"), PersistentDataType.STRING, name);
        e.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
    }

    public static @NotNull String capitalize(@NotNull String str){
        String[] words =str.split("\\s");
        StringBuilder cap= new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            cap.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return cap.toString().trim();
    }

    public enum WitherType {
        KASMIR(4, 3, 3,ChatColor.DARK_RED+"Kasmir", 1300),
        NECRON(3, 2.5d, 2,ChatColor.RED+"Necron", 1000),
        GOLDOR(2, 5, 5, ChatColor.GOLD+"Goldor", 1000),
        STORM(3, 3, 1, ChatColor.AQUA+"Storm", 1000),
        MAXOR(2, 2, 5, ChatColor.LIGHT_PURPLE+"Maxor", 1000),
        ;
        double dmg;
        double hp;
        String name;
        int lvl;
        double def;

        WitherType(double damageModifier, double healthModifier, double defenseModifier, String name, int level) {
            dmg = 35 * damageModifier;
            hp = 400 * healthModifier;
            hp = hp <= 1024 ? hp : 1024;
            this.name = name;
            def = 40 * defenseModifier;
            lvl = level;
        }
    }
}
