package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class MagicMagnet extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_INGOT, "Magic Magnet", SkyblockRarity.RARE,
                SkyblockItemType.ACCESSORY, new SkyblockItemStats()
        );
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY + "Attracts items to your inventory.",
                ChatColor.DARK_GRAY + "Because of it's powerful aura, might",
                ChatColor.DARK_GRAY + "buff certain mining items!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("magic_magnet");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
