package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class DiverHelmet extends SkyblockSkull {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(null, "Diver's Helmet",
                SkyblockRarity.LEGENDARY, SkyblockItemType.HELMET,
                new SkyblockItemStats().setHealth(60).setDefense(60).setStrength(100).setSeaCreatureChance(4));
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("diver_helmet");
    }

    @Override
    public @Nullable ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        ItemHelper.addAttribute(Attribute.GENERIC_ARMOR, 6, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, 6, m);
        addStrength(100, m);
        addSeaCreatureChance(4, m);
        m.addEnchant(Enchantment.WATER_WORKER, 5, true);
        m.addEnchant(Enchantment.OXYGEN, 5, true);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjljMjM5ZmFjYjI2NjFjMzNjMmRjMDc1YjVhYTgyNDIxYmU2MTkyYzM4YTZkNTc1YWFhMzZiN2ZiMDNlZDMyNyJ9fX0=";
    }
}
