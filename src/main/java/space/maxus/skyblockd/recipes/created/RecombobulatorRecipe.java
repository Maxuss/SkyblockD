package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class RecombobulatorRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("recombobulator_recipe"), SkyblockMaterial.RECOMBOBULATOR.getItem());
        r.shape(
                "OOO",
                "ECE",
                "OOO"
        );
        r.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        r.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_END_STONE.getItem()));
        r.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.RECOMBOBULATOR_CORE.getItem()));
        return r;
    }
}
