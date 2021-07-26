package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EnergeticHandleRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("energetic_handle_recipe"), SkyblockMaterial.ENERGETIC_HANDLE.getItem());
        rec.shape(
                "E",
                "E"
        );
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ASCENDED_ESSENCE.getItem()));
        return rec;
    }
}
