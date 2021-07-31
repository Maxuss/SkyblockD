package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class HeartOfSeaRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("heart_of_sea_recipe"), SkyblockMaterial.HEART_OF_SEA.getItem());
        rec.shape(
                "IDI",
                "DSD",
                "IDI"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COD.getItem()));
        rec.setIngredient('S', Material.HEART_OF_THE_SEA);
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_SALMON.getItem()));
        return rec;
    }
}
