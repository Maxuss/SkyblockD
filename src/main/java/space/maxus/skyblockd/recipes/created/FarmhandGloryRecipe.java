package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class FarmhandGloryRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("farmhand_glory_recipe"), SkyblockMaterial.FARMHAND_GLORY.getItem());
        rec.shape(
                "HMH",
                "BWS",
                "HMH"
        );
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_HAY.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_MELON.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_WET_SPONGE.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.FARMHAND_BLESSING.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.FARMHAND_SOUL.getItem()));
        return rec;
    }
}
