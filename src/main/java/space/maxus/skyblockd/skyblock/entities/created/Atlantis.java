package space.maxus.skyblockd.skyblock.entities.created;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class Atlantis extends SkyblockEntity {
    @Override
    public Location getLocation(Entity e) {
        return e.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        base.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        base.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        ItemStack leather = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) leather.getItemMeta();
        assert meta != null;
        meta.setColor(Color.fromRGB(30,91,117));
        leather.setItemMeta(meta);
        base.setLeggings(leather);
        base.setHelmet(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFhNzMyNTI0MDFhNmU5NDZmNjFkYmFjMGUwMjdkMTgzZTBhY2U1ODc1MmZhMTVhNjRkMjQ0OWZjZjUwODdiNyJ9fX0="));
        return base;
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public String getName() {
        return ChatColor.GOLD+"Atlantis";
    }

    @Override
    public double getHealth() {
        return 900;
    }

    @Override
    public double getDamage() {
        return 100;
    }

    @Override
    public double getDefense() {
        return 100;
    }

    @Override
    public int getLevel() {
        return 1000;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("atlantis");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("ATLANTIS"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
