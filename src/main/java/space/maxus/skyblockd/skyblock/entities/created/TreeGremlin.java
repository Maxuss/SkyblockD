package space.maxus.skyblockd.skyblock.entities.created;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class TreeGremlin extends SkyblockEntity {
    @Override
    public @NotNull Location getLocation(Entity e) {
        return e.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        base.setItemInMainHand(new ItemStack(Material.WOODEN_AXE));
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta m = (LeatherArmorMeta) chestplate.getItemMeta();
        m.setColor(Color.GREEN);
        chestplate.setItemMeta(m);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        leggings.setItemMeta(m);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        boots.setItemMeta(m);
        ItemStack helmet = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJmNTZjNjU0MWMxMjY2ZmZjNDYzNTEwYmRiNTVhZWY5MzE1YWY1NDg4OThjZjVkM2NiYTFiNWI0YzAxIn19fQ==");
        base.setHelmet(helmet);
        base.setChestplate(chestplate);
        base.setLeggings(leggings);
        base.setBoots(boots);
        return base;
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public @NotNull String getName() {
        return ChatColor.RED+"Tree Gremlin";
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
        return 30;
    }

    @Override
    public @Nullable String getSkyblockId() {
        return SkyblockD.getNamespace("tree_gremlin");
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
        Zombie z = (Zombie) entity;
        z.setBaby();
    }
}
