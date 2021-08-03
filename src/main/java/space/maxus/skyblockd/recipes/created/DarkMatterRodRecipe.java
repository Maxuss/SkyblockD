package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class DarkMatterRodRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("dark_matter_rod_recipe"), SkyblockMaterial.DARK_MATTER_ROD.getItem());

        rec.shape(
                "GEG",
                "ERE",
                "GEG"
        );
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('R', new RecipeChoice.ExactChoice(SkyblockMaterial.GOLDEN_ROD.getItem(), SkyblockMaterial.CORAL_ROD.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        return rec;
    }
}
