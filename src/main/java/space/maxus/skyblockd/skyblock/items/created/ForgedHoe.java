package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class ForgedHoe extends SkyblockItem implements FarmingHoe {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_HOE, "Forged Hoe", SkyblockRarity.LEGENDARY,
                SkyblockItemType.HOE, new SkyblockItemStats().setStrength(40)
        );
        cfg.setAbilities(addFarmingAbility(10, "Reforged Farmer"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("forged_hoe");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        addFarmingFortune(50, m);
        addStrength(40, m);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return null;
    }
}
