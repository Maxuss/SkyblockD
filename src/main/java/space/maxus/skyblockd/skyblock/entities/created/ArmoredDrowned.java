package space.maxus.skyblockd.skyblock.entities.created;

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

public class ArmoredDrowned extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        base.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROWNED;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Armored Drowned";
    }

    @Override
    public double getHealth() {
        return 50;
    }

    @Override
    public double getDamage() {
        return 10;
    }

    @Override
    public double getDefense() {
        return 10;
    }

    @Override
    public int getLevel() {
        return 25;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("drowned_plus");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("ARMORED_DROWNED"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
