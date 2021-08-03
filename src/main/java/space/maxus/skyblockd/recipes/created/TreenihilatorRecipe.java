package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class TreenihilatorRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("treenihilator_recipe"), SkyblockMaterial.TREENIHILATOR.getItem());
        rec.shape(
                "DSO",
                "DTO",
                "DWO"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_SINGULARITY.getItem()));
        rec.setIngredient('T', new RecipeChoice.ExactChoice(SkyblockMaterial.TREECAPITATOR.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.WOODEN_SINGULARITY.getItem()));
        return rec;
    }
}
