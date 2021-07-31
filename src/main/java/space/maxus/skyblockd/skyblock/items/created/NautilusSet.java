package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class NautilusSet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return "nautilus_set";
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.NAUTILUS_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.NAUTILUS_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.NAUTILUS_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.NAUTILUS_BOOTS.getItem();
    }
}
