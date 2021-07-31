package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class GalacticaSingularityRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("galactica_singularity_recipe"), SkyblockMaterial.GALACTICA_SINGULARITY.getItem());

        rec.shape(
                "JJJ",
                "WID",
                "EEE"
        );

        rec.setIngredient('J', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ASCENDED_ESSENCE.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_SINGULARITY.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.FRONTIER.getItem()));

        return rec;
    }
}
