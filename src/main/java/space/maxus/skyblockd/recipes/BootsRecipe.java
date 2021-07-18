package space.maxus.skyblockd.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.Locale;

// simple boots recipe
public abstract class BootsRecipe extends RecipeBase<ShapedRecipe> implements SkyblockFeature {
    public abstract Material getMaterial();
    public abstract ItemStack getResult();
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey(getSkyblockId().replace("skyblockd:", "").toLowerCase(Locale.ENGLISH)), getResult());
        r.shape(
                "   ",
                "B B",
                "B B"
        );
        r.setIngredient('B', getMaterial());
        return r;
    }
}
