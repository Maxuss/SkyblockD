package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class FarmhandSoulRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("farmhand_soul_recipe"), SkyblockMaterial.FARMHAND_SOUL.getItem());
        rec.shape(
                "PCW",
                "BGB",
                "WCP"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_POTATO.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_CARROT.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_WHEAT.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BEETROOT.getItem()));
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_GHAST_TEAR.getItem()));
        return rec;
    }
}
