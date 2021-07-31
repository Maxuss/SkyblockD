package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;

public class EmeraldSet extends ArmorSet {
    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("emerald_set");
    }

    @Override
    public ItemStack getHelmet() {
        return new EmeraldHelmet().generate();
    }

    @Override
    public ItemStack getChestplate() {
        return new EmeraldChestplate().generate();
    }

    @Override
    public ItemStack getLeggings() {
        return new EmeraldLeggings().generate();
    }

    @Override
    public ItemStack getBoots() {
        return new EmeraldBoots().generate();
    }
}
