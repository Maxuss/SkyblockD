package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class GrailPartOne extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GOLD_INGOT, "Holy Grail Handle",
                SkyblockRarity.EPIC, SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats()
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"...from the deepest of hollows..."));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("grail_1");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
