package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SacredOrbRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("sacred_orb_recipe"), SkyblockMaterial.SACRED_ORB.getItem());
        rec.shape(
                "IDI",
                "DSD",
                "IDI"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_GOLD_BLOCK.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.LOST_CROWN.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.GEMSTONE.getItem()));
        return rec;
    }
}
