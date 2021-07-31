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

public class DrownedRemnant extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        base.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        base.setHelmet(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdiOWRmZDI4MWRlYWVmMjYyOGFkNTg0MGQ0NWJjZGE0MzZkNjYyNjg0NzU4N2YzYWM3NjQ5OGE1MWM4NjEifX19"));
        base.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DROWNED;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Drowned Remnant";
    }

    @Override
    public double getHealth() {
        return 300;
    }

    @Override
    public double getDamage() {
        return 25;
    }

    @Override
    public double getDefense() {
        return 20;
    }

    @Override
    public int getLevel() {
        return 150;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("drowned_remnant");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("DROWNED_REMNANT"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
