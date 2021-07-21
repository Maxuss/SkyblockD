package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SimpleGuideRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("guide_recipe_1"), SkyblockMaterial.RECIPE_GUIDE_1.getItem());
        r.shape(
                "QLQ",
                "LBL",
                "QLQ"
        );
        r.setIngredient('Q', Material.QUARTZ);
        r.setIngredient('L', Material.LAPIS_LAZULI);
        r.setIngredient('B', Material.BOOK);
        return r;
    }
}
