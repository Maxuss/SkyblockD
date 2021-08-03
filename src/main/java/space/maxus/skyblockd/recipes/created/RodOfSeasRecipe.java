package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class RodOfSeasRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("rod_of_seas_recipe"), SkyblockMaterial.ROD_OF_SEAS.getItem());

        rec.shape(
                "LEL",
                "ERE",
                "LEL"
        );
        rec.setIngredient('L', Material.LILY_PAD);
        rec.setIngredient('R', new RecipeChoice.ExactChoice(SkyblockMaterial.REINFORCED_ROD.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_LILY_PAD.getItem()));
        return rec;
    }
}
