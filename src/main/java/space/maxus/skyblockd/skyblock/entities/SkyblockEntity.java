package space.maxus.skyblockd.skyblock.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

public abstract class SkyblockEntity implements SkyblockFeature {

    public abstract Location getLocation(Entity e);
    public abstract EntityEquipment getEquipment(EntityEquipment base);
    public abstract EntityType getType();
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getDamage();
    public abstract double getDefense();
    public abstract int getLevel();
    public abstract String getSkyblockId();
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

    public Entity generate(Entity en){
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

    public static void toSkyblockEntity(LivingEntity e){
        String name;
        if(e.getCustomName() == null){
            name = ChatColor.RED + capitalize(e.getType().toString().toLowerCase(Locale.ENGLISH).replace("_", " "));
        } else name = e.getCustomName();

        Biome entityBiome = e.getLocation().getBlock().getBiome();

        double maxHp = Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();

        boolean isEnd = entityBiome.name().contains("END");

        if(nether.contains(entityBiome) && maxHp <= 20) {
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHp * 2);
            e.setHealth(maxHp * 2);
        } else if(isEnd && maxHp <= 40) {
            Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHp * 4);
            e.setHealth(maxHp * 4);
            maxHp = Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
        }


        if(isEnd) {
            int tx = Math.abs(e.getLocation().getBlockX());
            float multiplier = tx <= 200 ? 1 : tx <= 4000 ? tx / 1000f : 4;
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

    public static String capitalize(String str){
        String[] words =str.split("\\s");
        StringBuilder cap= new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            cap.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return cap.toString().trim();
    }
}
