package space.maxus.skyblockd.skyblock.entities.created;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class LostDiver extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setHelmet(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjljMjM5ZmFjYjI2NjFjMzNjMmRjMDc1YjVhYTgyNDIxYmU2MTkyYzM4YTZkNTc1YWFhMzZiN2ZiMDNlZDMyNyJ9fX0="));
        base.setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        base.setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
        base.setBoots(new ItemStack(Material.GOLDEN_BOOTS));
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Lost Diver";
    }

    @Override
    public double getHealth() {
        return 450;
    }

    @Override
    public double getDamage() {
        return 10;
    }

    @Override
    public double getDefense() {
        return 30;
    }

    @Override
    public int getLevel() {
        return 150;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("lost_diver");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("LOST_DIVER"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
