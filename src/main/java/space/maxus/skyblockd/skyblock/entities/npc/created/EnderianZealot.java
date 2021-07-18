package space.maxus.skyblockd.skyblock.entities.npc.created;

import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.npc.BehaviourType;
import space.maxus.skyblockd.skyblock.entities.npc.SkyblockNPC;

import javax.annotation.Nullable;

public class EnderianZealot extends SkyblockNPC {
    @Override
    public Location getLocation(Entity e) {
        return e.getLocation();
    }

    @Override
    public Equipment setEquipment(Equipment base) {
        ItemStack leather = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta m = (LeatherArmorMeta) leather.getItemMeta();
        assert m != null;
        m.setColor(Color.PURPLE);
        leather.setItemMeta(m);
        base.set(Equipment.EquipmentSlot.CHESTPLATE, leather);
        base.set(Equipment.EquipmentSlot.BOOTS, new ItemStack(Material.CHAINMAIL_BOOTS));
        base.set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_AXE));
        return base;
    }

    @Override
    public String getName() {
        return ChatColor.DARK_PURPLE+"Enderian Zealot";
    }

    @Override
    public double getHealth() {
        return 700;
    }

    @Override
    public double getDamage() {
        return 20;
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
    public String getSkyblockId() {
        return SkyblockD.getNamespace("enderian_zealot");
    }

    @Override
    public BehaviourType getBehaviour() {
        return BehaviourType.AGGRESSIVE;
    }

    @Override
    public void postInit(LivingEntity entity, Entity base) {
    }

    @Nullable
    @Override
    public String getSkinHash() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYyNjUyNzE5NzcxNywKICAicHJvZmlsZUlkIiA6ICI5MGQ1NDY0OGEzNWE0YmExYTI2Yjg1YTg4NTU4OGJlOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJFdW4wbWlhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZlZTE2MzZjOTgzNzE3M2UyNzNlMTBiODMxYTIwZTM1NmFmNTIyODk1M2NkMjYzY2ExZDZiZmY1ZTA0OTc1NGUiCiAgICB9CiAgfQp9";
    }

    @Nullable
    @Override
    public String getSkinSignature() {
        return "o1KR5BKpsKkV9PhKKcd/6Yr92jgFl4mMmsFJvuc/CeVOlPgXRRRSDtzBQukv2YCQOFCqfXl24FZHBJBAIj3riZsBnA4xzvCtZ7qgRBFFP8ahE2tyX/l85CfYincVK0c93DN4sBw17Fr0y24tx5X8oJd0T3dKMNvv1+G7AzdAwEKUUBWqzSXTiHbzfgG4i5aozLEqaLI2VLK4yIMVY1z7bnG3OnONJZZDpAob6wuATYUe9oVku83FVU2ec6lPJSIJQNqSXVI9bv22QHBIa18zrZavgo3cEgPi5rrh51SuQesTar5RhQ6EMaHfsmZwe7UyJodGuvoEBLn1Hbfqp0r3UWNPNWUkOlC0hB1xeEOEODCZgnXBoreiCeBmL/yEzMJZ5Rhxm7Nm6dHcXUj8blyTiOe72EELm5qF4JNG7OOT1GYZlERXvTXA3VNApgA9Dd1dtcFaXH0aVcVDb/3k0eDPGpy/uPliZdazrKKsqexHjVY2M35ePCs/BpVoJY+y/9e0cKCYbbd7ar4nCsWEZbGZ+rAYLfzezX2Gloeqi+T0QsRycIIftXuQ8ZS2aYUub/m5HpIpZqXVYFXMSRCqt33ePvsEk8DbC9FcIUJEQ4beBUTl99rYrAzqV7Fmdguw5joy9ZCoxdUVC7e2LEphbJ8mI98+jEu4QGETzPm1TACkzXk=";
    }
}
