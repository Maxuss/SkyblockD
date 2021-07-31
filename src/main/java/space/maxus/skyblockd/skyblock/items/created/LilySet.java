package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class LilySet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("emerald_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.LILY_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.LILY_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.LILY_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.LILY_BOOTS.getItem();
    }
}
