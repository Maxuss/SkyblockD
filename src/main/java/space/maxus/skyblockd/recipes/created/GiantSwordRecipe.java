package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class GiantSwordRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("giant_sword_recipe"), SkyblockMaterial.GIANT_SWORD.getItem());

        rec.shape(
                "BDB",
                "BSB",
                "BHB"
        );
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_IRON_BLOCK.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENERGETIC_HANDLE.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.GOLEM_SWORD.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));

        return rec;
    }
}
