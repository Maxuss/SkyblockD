package space.maxus.skyblockd.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.Locale;

public abstract class LeggingsRecipe extends RecipeBase<ShapedRecipe> implements SkyblockFeature {
    public abstract @NotNull Material getMaterial();
    public abstract ItemStack getResult();

    public LeggingsRecipe() {
        super();
    }

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey(getSkyblockId().replace("skyblockd:", "").toLowerCase(Locale.ENGLISH)), getResult());
        r.shape(
                "BBB",
                "B B",
                "B B"
        );
        r.setIngredient('B', getMaterial());
        return r;
    }
}
