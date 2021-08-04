package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class BonemerangRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("bonemerang_recipe"), SkyblockMaterial.BONEMERANG.getItem());

        rec.shape(
                "BBB",
                "MFM",
                "BBB"
        );
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BONE_BLOCK.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_MOON_STONE.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(SkyblockMaterial.ERODED_FOSSIL.getItem()));

        return rec;
    }
}
