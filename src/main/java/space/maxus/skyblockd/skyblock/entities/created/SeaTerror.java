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
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class SeaTerror extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setHelmet(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM1NTQxNTIzZjE0Yzc4ZDhkZTk4Y2MyOTZjNzk4ZjBiODY3YmE4NTM0NGVkNzdmNmRhZTEyNDU1YTE3NCJ9fX0="));
        ItemStack leatherChest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) leatherChest.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(true);
        meta.setColor(Color.fromRGB(19,117,55));
        leatherChest.setItemMeta(meta);
        ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS);
        leatherBoots.setItemMeta(meta);
        base.setChestplate(leatherChest);
        base.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        base.setBoots(leatherBoots);
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.GOLD+"Sea Terror";
    }

    @Override
    public double getHealth() {
        return 900;
    }

    @Override
    public double getDamage() {
        return 60;
    }

    @Override
    public double getDefense() {
        return 100;
    }

    @Override
    public int getLevel() {
        return 600;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("sea_terror");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("SEA_TERROR"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
