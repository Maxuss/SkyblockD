package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class MasterCrownRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("master_crown_recipe"), SkyblockMaterial.MASTER_CROWN.getItem());
        rec.shape(
                "GDG",
                "GMG"
        );
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_GOLD_BLOCK.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_EMERALD_BLOCK.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.MADNESS.getItem()));

        return rec;
    }
}
