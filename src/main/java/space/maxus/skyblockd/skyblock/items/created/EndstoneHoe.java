package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.UUID;

public class EndstoneHoe extends SkyblockItem implements FarmingHoe {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GOLDEN_HOE, "Endstone Hoe", SkyblockRarity.RARE,
                SkyblockItemType.HOE, new SkyblockItemStats().setSpeed(20)
        );
        cfg.setAbilities(addFarmingAbility(2, "End Stone Farmer"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("endstone_hoe");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier(
                        UUID.randomUUID(), "generic.movementSpeed",
                        0.02d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        addFarmingFortune(10, m);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return null;
    }
}
