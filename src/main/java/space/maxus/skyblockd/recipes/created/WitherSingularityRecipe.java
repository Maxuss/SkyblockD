package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class WitherSingularityRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("wither_singularity_recipe") , SkyblockMaterial.WITHER_SINGULARITY.getItem());
        rec.shape(
                " S ",
                "UEY",
                "OMC"
        );
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.NECRON_SOUL_FRAGMENT.getItem()));
        rec.setIngredient('U', new RecipeChoice.ExactChoice(SkyblockMaterial.KASMIR_SOUL_FRAGMENT.getItem()));
        rec.setIngredient('Y', new RecipeChoice.ExactChoice(SkyblockMaterial.MAXOR_SOUL_FRAGMENT.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.GOLDOR_SOUL_FRAGMENT.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.STORM_SOUL_FRAGMENT.getItem()));
        return rec;
    }
}
