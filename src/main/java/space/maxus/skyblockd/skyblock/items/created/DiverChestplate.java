package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class DiverChestplate extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(Material.GOLDEN_CHESTPLATE, "Diver's Chestplate",
                SkyblockRarity.LEGENDARY, SkyblockItemType.CHESTPLATE,
                new SkyblockItemStats().setHealth(80).setDefense(80).setStrength(100).setSeaCreatureChance(4));
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("diver_chestplate");
    }

    @Override
    public @Nullable ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        ItemHelper.addAttribute(Attribute.GENERIC_ARMOR, 8, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, 8, m);
        addStrength(100, m);
        addSeaCreatureChance(4, m);
        i.setItemMeta(m);
        return i;
    }
}
