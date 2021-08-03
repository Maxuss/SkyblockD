package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
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

public class DiverBoots extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(Material.GOLDEN_BOOTS, "Diver's Boots",
                SkyblockRarity.LEGENDARY, SkyblockItemType.BOOTS,
                new SkyblockItemStats().setHealth(60).setDefense(60).setStrength(100).setSeaCreatureChance(4));
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("diver_boots");
    }

    @Override
    public @Nullable ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        ItemHelper.addAttribute(Attribute.GENERIC_ARMOR, 6, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, 6, m);
        addSeaCreatureChance(4, m);
        addStrength(100, m);
        m.addEnchant(Enchantment.DEPTH_STRIDER, 5, true);
        i.setItemMeta(m);
        return i;
    }
}
