package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class DraconicShortbowRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("draconic_shortbow_recipe"), SkyblockMaterial.DRACONIC_SHORTBOW.getItem());

        rec.shape(
                "AGA",
                "EBO",
                "AHA"
        );

        rec.setIngredient('A', new RecipeChoice.ExactChoice(SkyblockMaterial.ASCENDED_ESSENCE.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GEMSTONE_SHORTBOW.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.EMERALD_SHORTBOW.getItem()));
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.OBSIDIAN_SHORTBOW.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.HOLY_SHORTBOW.getItem()));

        return rec;
    }
}
