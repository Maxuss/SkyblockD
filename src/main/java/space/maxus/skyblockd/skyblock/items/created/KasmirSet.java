package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class KasmirSet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("kasmir_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.KASMIR_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.KASMIR_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.KASMIR_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.KASMIR_BOOTS.getItem();
    }
}
