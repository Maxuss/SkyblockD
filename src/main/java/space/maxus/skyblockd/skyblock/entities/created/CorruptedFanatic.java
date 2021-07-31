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
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class CorruptedFanatic extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity p) {
        return p.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setHelmet(new ItemStack(Material.MAGENTA_STAINED_GLASS));
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMAN;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.LIGHT_PURPLE+"Corrupted Fanatic";
    }

    @Override
    public double getHealth() {
        return 350;
    }

    @Override
    public double getDamage() {
        return 15;
    }

    @Override
    public double getDefense() {
        return 5;
    }

    @Override
    public int getLevel() {
        return 100;
    }

    @Override
    public @Nullable String getSkyblockId() {
        return null;
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FANATIC"), PersistentDataType.BYTE, (byte)1);
    }
}
