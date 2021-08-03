package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ShadowGogglesRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("shadow_goggles_recipe"), SkyblockMaterial.SHADOW_GOGGLES.getItem());

        rec.shape(
                "OOO",
                "ESE"
        );
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_EYE_OF_ENDER.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.SHADED_ORB.getItem()));
        return rec;
    }
}
