package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class RustyPlatingRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("rusty_plating_recipe"), SkyblockMaterial.RUSTY_PLATING.getItem());
        rec.shape(
                "DDD",
                "DSD",
                "DDD"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COBBLESTONE.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem()));
        return rec;
    }
}
