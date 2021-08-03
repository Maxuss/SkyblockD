package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class MadnessRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("madness_recipe"), SkyblockMaterial.MADNESS.getItem());
        rec.shape(
                "GDG",
                "GMG"
        );
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_GOLD_BLOCK.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.GALACTICA_SINGULARITY.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.KASMIR_HELMET.getItem()));

        return rec;
    }
}
