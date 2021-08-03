package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class CoralRodRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("coral_rod_recipe"), SkyblockMaterial.CORAL_ROD.getItem());

        rec.shape(
                "GEG",
                "ERE",
                "GEG"
        );
        rec.setIngredient('G', Material.BRAIN_CORAL_BLOCK);
        rec.setIngredient('R', new RecipeChoice.ExactChoice(SkyblockMaterial.ROD_OF_SEAS.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem()));
        return rec;
    }
}
