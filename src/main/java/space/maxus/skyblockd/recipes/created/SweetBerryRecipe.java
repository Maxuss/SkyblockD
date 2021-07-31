package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SweetBerryRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("sweetberry_recipe"), SkyblockMaterial.SWEET_BERRY.getItem());
        rec.shape(
                "IDI",
                "DSD",
                "IDI"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BEETROOT.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_SUGAR_CANE.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_POTATO.getItem()));
        return rec;
    }
}
