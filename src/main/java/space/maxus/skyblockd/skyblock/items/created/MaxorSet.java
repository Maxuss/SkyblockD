package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class MaxorSet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("maxor_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.MAXOR_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.MAXOR_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.MAXOR_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.MAXOR_BOOTS.getItem();
    }
}
