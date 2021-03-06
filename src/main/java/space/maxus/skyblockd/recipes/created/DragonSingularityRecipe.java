package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class DragonSingularityRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("dragon_singularity_recipe") ,SkyblockMaterial.DRAGON_SINGULARITY.getItem());
        rec.shape(
                " S ",
                "UEY",
                "OMC"
        );
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.STRONG_FRAGMENT.getItem()));
        rec.setIngredient('U', new RecipeChoice.ExactChoice(SkyblockMaterial.SUPERIOR_FRAGMENT.getItem()));
        rec.setIngredient('Y', new RecipeChoice.ExactChoice(SkyblockMaterial.YOUNG_FRAGMENT.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_MOON_STONE.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ASCENDED_ESSENCE.getItem()));
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.OLD_FRAGMENT.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.UNSTABLE_FRAGMENT.getItem()));
        return rec;
    }
}
