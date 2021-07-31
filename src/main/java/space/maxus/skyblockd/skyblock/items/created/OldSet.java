package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class OldSet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("old_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.OLD_DRAGON_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.OLD_DRAGON_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.OLD_DRAGON_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.OLD_DRAGON_BOOTS.getItem();
    }
}
