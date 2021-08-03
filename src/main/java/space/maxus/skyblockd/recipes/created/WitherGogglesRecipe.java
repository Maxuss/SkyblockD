package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class WitherGogglesRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("wither_goggles_recipe"), SkyblockMaterial.WITHER_GOGGLES.getItem());

        rec.shape(
                "SSS",
                "EGE"
        );
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.SHADOW_GOGGLES.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.STORM_SOUL_FRAGMENT.getItem()));
        return rec;
    }
}
