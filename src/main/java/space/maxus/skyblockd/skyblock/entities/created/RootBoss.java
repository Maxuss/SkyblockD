package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

import java.util.Objects;

public class RootBoss extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.IRON_GOLEM;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.DARK_GREEN+"The Root";
    }

    @Override
    public double getHealth() {
        return 800;
    }

    @Override
    public double getDamage() {
        return 35;
    }

    @Override
    public double getDefense() {
        return 150;
    }

    @Override
    public int getLevel() {
        return 1000;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("root_boss");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 1000, 5, false, true);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.5d);
        entity.addPotionEffect(regen);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("ROOT_BOSS"), PersistentDataType.BYTE, (byte)0);
        entity.damage(1, base);
    }
}
