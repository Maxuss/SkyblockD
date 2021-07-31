package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class GemstoneRecipe extends RecipeBase<ShapelessRecipe> {
    @Override
    public @NotNull ShapelessRecipe getRecipe() {
        ShapelessRecipe rec = new ShapelessRecipe(SkyblockD.getKey("gemstone_recipe"), SkyblockMaterial.GEMSTONE.getItem());
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_LAPIS_LAZULI.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_REDSTONE.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DIAMOND.getItem()));
        return rec;
    }
}
