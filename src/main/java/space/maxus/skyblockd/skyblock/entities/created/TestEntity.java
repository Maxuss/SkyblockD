package space.maxus.skyblockd.skyblock.entities.created;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class TestEntity extends SkyblockEntity {
    @Override
    public Location getLocation(Player p) {
        return p.getLocation();
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment base) {
        base.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        base.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        base.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        base.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        base.setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
        base.setItemInOffHand(new ItemStack(Material.DIAMOND_AXE));
        return base;
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public String getName() {
        return ChatColor.RED+"Buffed Zombie";
    }

    @Override
    public double getHealth() {
        return 350;
    }

    @Override
    public double getDamage() {
        return 5;
    }

    @Override
    public double getDefense() {
        return 3;
    }

    @Override
    public int getLevel() {
        return 120;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("test_entity");
    }

    @Override
    public void postInit(LivingEntity entity) {
        SkyblockD.getSender().sendMessage(ChatColor.GRAY+"Generated entity!");
    }

    public TestEntity(Player p) {
        super.generate(p);
    }
}
