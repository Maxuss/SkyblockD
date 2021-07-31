package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

public class SeaArcher extends SkyblockEntity {

    @Override
    public @NotNull Location getLocation(@NotNull Entity e) {
        return e.getLocation();
    }

    @Override
    public @NotNull EntityEquipment getEquipment(@NotNull EntityEquipment base) {
        base.setBoots(new ItemStack(Material.IRON_BOOTS));
        ItemStack leather = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) leather.getItemMeta();
        assert meta != null;
        meta.setColor(Color.BLUE);
        leather.setItemMeta(meta);
        base.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        base.setChestplate(leather);
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        base.setItemInMainHand(bow);
        base.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));

        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SKELETON;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Sea Archer";
    }

    @Override
    public double getHealth() {
        return 100;
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
        return 50;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("ocean_sailor");
    }

    @Override
    public void postInit(@NotNull LivingEntity entity, Entity base) {
        entity.getPersistentDataContainer().set(SkyblockD.getKey("OCEAN_SAILOR"), PersistentDataType.BYTE, (byte)1);
        entity.getPersistentDataContainer().set(SkyblockD.getKey("FISHED"), PersistentDataType.BYTE, (byte)1);
    }
}
