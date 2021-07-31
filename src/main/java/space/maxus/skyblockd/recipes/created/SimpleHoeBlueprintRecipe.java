package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SimpleHoeBlueprintRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("simple_hoe_blueprint_recipe"), SkyblockMaterial.SIMPLE_HOE_BLUEPRINT.getItem());
        rec.shape(
                "DDD",
                "DSD",
                "DDD"
        );
        rec.setIngredient('D', Material.OAK_PLANKS);
        rec.setIngredient('S', Material.IRON_HOE);
        return rec;
    }
}
