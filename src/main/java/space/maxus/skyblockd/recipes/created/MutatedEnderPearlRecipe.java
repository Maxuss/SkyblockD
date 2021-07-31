package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class MutatedEnderPearlRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("mutated_ender_pearl_recipe"), SkyblockMaterial.MUTATED_ENDER_PEARL.getItem());
        rec.shape(
                "WDW",
                "DSD",
                "IDI"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_EYE_OF_ENDER.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.MUTATED_FLESH.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_END_STONE.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_MOON_STONE.getItem()));
        return rec;
    }
}
