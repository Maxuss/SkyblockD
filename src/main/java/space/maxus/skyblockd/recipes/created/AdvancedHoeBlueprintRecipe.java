package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class AdvancedHoeBlueprintRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("advanced_hoe_blueprint_recipe"), SkyblockMaterial.ADVANCED_HOE_BLUEPRINT.getItem());
        rec.shape(
                "DDD",
                "DSD",
                "DDD"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_WARPED_STEM.getItem(), SkyblockMaterial.ENCHANTED_CRIMSON_STEM.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.SIMPLE_HOE_BLUEPRINT.getItem()));
        return rec;
    }
}
