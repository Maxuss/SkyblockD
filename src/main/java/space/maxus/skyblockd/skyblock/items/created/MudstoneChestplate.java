package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Collections;

public class MudstoneChestplate extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_CHESTPLATE, "Mudstone Chestplate", SkyblockRarity.RARE,
                SkyblockItemType.CHESTPLATE, new SkyblockItemStats().setHealth(25).setSpeed(10)
        );
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility(
                "Mudstone Excavator", SkyblockAbilityType.PASSIVE,
                Collections.singletonList(ChatColor.GOLD+"+5 "+ SkyblockConstants.FORTUNE + " Excavating Fortune"))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("mudstone_chestplate");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 2.5d, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.01d, AttributeModifier.Operation.ADD_NUMBER));
        addExcavatingFortune(10, m);
        i.setItemMeta(m);
        return i;
    }
}
