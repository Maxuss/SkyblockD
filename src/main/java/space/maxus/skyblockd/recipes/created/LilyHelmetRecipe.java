package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.HelmetRecipe;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class LilyHelmetRecipe extends HelmetRecipe {
    @Override
    public @NotNull Material getMaterial() {
        return Material.LILY_PAD;
    }

    @Override
    public ItemStack getResult() {
        return SkyblockMaterial.LILY_HELMET.getItem();
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("lily_helmet_recipe");
    }
}
