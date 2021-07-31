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

public class HardwoodHoe extends SkyblockItem implements FarmingHoe {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.WOODEN_HOE, "Hardwood Hoe", SkyblockRarity.UNCOMMON,
                SkyblockItemType.HOE, new SkyblockItemStats().setDefense(40)
        );
        cfg.setAbilities(addFarmingAbility(3, "Experienced Farmer"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("hardwood_hoe");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_ARMOR,
                new AttributeModifier(
                        UUID.randomUUID(), "generic.armor",
                        4d, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        addFarmingFortune(15, m);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return null;
    }
}
