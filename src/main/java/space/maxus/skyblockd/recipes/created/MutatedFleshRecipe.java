package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class MutatedFleshRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("mutated_flesh_recipe"), SkyblockMaterial.MUTATED_FLESH.getItem());
        rec.shape(
                "WDW",
                "DSD",
                "IDI"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_ROTTEN_FLESH.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_STRING.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BONE.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_SPIDER_EYE.getItem()));
        return rec;
    }
}
