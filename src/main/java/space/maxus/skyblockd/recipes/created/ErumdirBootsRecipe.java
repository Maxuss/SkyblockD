package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ErumdirBootsRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("erumdir_boots_recipe"), SkyblockMaterial.ERUMDIR_BOOTS.getItem());

        rec.shape(
                "DNQ",
                "SFM",
                "QGD"
        );

        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK.getItem()));
        rec.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_QUARTZ_BLOCK.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(SkyblockMaterial.ERUMDIR_SOUL_FRAGMENT.getItem()));

        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.STORM_BOOTS.getItem()));
        rec.setIngredient('N', new RecipeChoice.ExactChoice(SkyblockMaterial.NECRON_BOOTS.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.MAXOR_BOOTS.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GOLDOR_BOOTS.getItem()));

        return rec;
    }
}
