package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class SimpleHoeBlueprint extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.WOODEN_HOE, "Simple Hoe Blueprint", SkyblockRarity.UNCOMMON,
                SkyblockItemType.OTHER_NONCONSUMABLE, new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY+"This is a base ingredient for",
                ChatColor.DARK_GRAY+"making better farming hoes."
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("hoe_blueprint_1");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }
}
