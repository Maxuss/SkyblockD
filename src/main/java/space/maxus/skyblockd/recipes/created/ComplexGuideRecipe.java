package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ComplexGuideRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("guide_recipe_2"), SkyblockMaterial.RECIPE_GUIDE_2.getItem());
        r.shape(
                "QLQ",
                "LBL",
                "QLQ"
        );
        r.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_QUARTZ.getItem()));
        r.setIngredient('L', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_LAPIS_LAZULI.getItem()));
        r.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.RECIPE_GUIDE_1.getItem()));
        return r;
    }
}
