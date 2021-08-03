package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class TuxedoBootsRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("tuxedo_boots_recipe"), SkyblockMaterial.TUXEDO_BOOTS.getItem());

        rec.shape(
                "WGW",
                "WBW",
                " D "
        );
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GALACTICA_SINGULARITY.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.KASMIR_BOOTS.getItem()));

        return rec;
    }
}
