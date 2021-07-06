package space.maxus.skyblockd.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;

public class TestEntity extends ExpandedEntity<LivingEntity> {

    @Override
    public Location getPosition(Player p) {
        return p.getLocation();
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public String getId() {
        return SkyblockD.getNamespace("entity_test");
    }

    @Override
    public void initializationLogic(LivingEntity e) {
        e.setCustomName(ChatColor.RED + "Sus Imposter!");
        e.setCustomNameVisible(true);
        EntityEquipment q = e.getEquipment();
        ItemStack b = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack l = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack c = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack h = new ItemStack(Material.LEATHER_HELMET, 1);
        assert q != null : "Could not generate equipment!";
        q.setBoots(b);
        q.setLeggings(l);
        q.setChestplate(c);
        q.setHelmet(h);
    }

    public TestEntity(Player p) {
        super(p);
    }
}
