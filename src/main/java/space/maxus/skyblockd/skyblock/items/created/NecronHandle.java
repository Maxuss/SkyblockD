package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class NecronHandle extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(
                Material.STICK, "Necron's Handle", SkyblockRarity.EPIC,
                SkyblockItemType.OTHER_NONCONSUMABLE, new SkyblockItemStats());
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("necron_handle");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
