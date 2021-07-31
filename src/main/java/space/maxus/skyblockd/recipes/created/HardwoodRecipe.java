package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class HardwoodRecipe extends RecipeBase<ShapelessRecipe> {
    @Override
    public @NotNull ShapelessRecipe getRecipe() {
        ShapelessRecipe rec = new ShapelessRecipe(SkyblockD.getKey("hardwood_recipe"), SkyblockMaterial.HARDWOOD.getItem());
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_ACACIA_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_JUNGLE_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DARK_OAK_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OAK_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BIRCH_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_SPRUCE_LOG.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_CRIMSON_STEM.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_WARPED_STEM.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_ROCK_PILE.getItem()));
        return rec;
    }
}
