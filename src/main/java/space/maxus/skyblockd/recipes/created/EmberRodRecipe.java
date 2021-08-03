package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EmberRodRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("ember_rod_recipe"), SkyblockMaterial.EMBER_ROD.getItem());

        rec.shape(
                "P",
                "P",
                "B"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BLAZE_POWDER.getItem()));
        rec.setIngredient('B', Material.BLAZE_ROD);

        return rec;
    }
}
