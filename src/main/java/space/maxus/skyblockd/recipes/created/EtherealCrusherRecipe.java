package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EtherealCrusherRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("ethereal_crusher_recipe"), SkyblockMaterial.ETHEREAL_CRUSHER.getItem());

        rec.shape(
                "QDQ",
                " H ",
                " B "
        );
        rec.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENERGETIC_HANDLE.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK.getItem()));

        return rec;
    }
}
