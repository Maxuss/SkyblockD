package space.maxus.skyblockd.skyblock.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.Locale;
import java.util.Objects;

public abstract class SkyblockEntity implements SkyblockFeature {

    public abstract Location getLocation(Player p);
    public abstract EntityEquipment getEquipment(EntityEquipment base);
    public abstract EntityType getType();
    public abstract String getName();
    public abstract double getHealth();
    public abstract double getDamage();
    public abstract double getDefense();
    public abstract int getLevel();
    public abstract String getSkyblockId();
    public abstract void postInit(LivingEntity entity);

    public void generate(Player p){
        LivingEntity e = (LivingEntity) p.getWorld().spawnEntity(getLocation(p), getType());

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
                ChatColor.GRAY + "[" + ChatColor.DARK_GRAY + getLevel() + ChatColor.GRAY + "]" + " "
                + getName() + ChatColor.RESET + " " + ChatColor.GREEN + getHealth() + ChatColor.WHITE
                + "/" + ChatColor.GREEN + (int) getHealth() + ChatColor.RED + " " + SkyblockConstants.HEALTH
        );

        addSkyblockTag(e);
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityName"), PersistentDataType.STRING, getName());
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER, getLevel());

        // finish initialization by calling postInit
        postInit(e);
    }

    public static void toSkyblockEntity(LivingEntity e){
        String name;
        if(e.getCustomName() == null){
            name = ChatColor.RED + capitalize(e.getType().toString().toLowerCase(Locale.ENGLISH).replace("_", " "));
        } else name = e.getCustomName();

        int lvl = (int) (e.getHealth() / 2.1);

        e.setCustomNameVisible(true);
        e.setCustomName(
                ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + lvl + ChatColor.DARK_GRAY + "]" + " "
                        + name + ChatColor.RESET + " " + ChatColor.GREEN + (int) e.getHealth() + ChatColor.WHITE
                        + "/" + ChatColor.GREEN +
                        (int) Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()
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
