package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ErumdirHelmetRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("erumdir_helmet_recipe"), SkyblockMaterial.ERUMDIR_HELMET.getItem());

        rec.shape(
                "DNQ",
                "SFM",
                "QGJ"
        );

        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_DARK_PRISMARINE.getItem()));
        rec.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_CHORUS_FLOWER.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(SkyblockMaterial.ERUMDIR_SOUL_FRAGMENT.getItem()));

        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.STORM_HELMET.getItem()));
        rec.setIngredient('N', new RecipeChoice.ExactChoice(SkyblockMaterial.NECRON_HELMET.getItem()));
        rec.setIngredient('M', new RecipeChoice.ExactChoice(SkyblockMaterial.MAXOR_HELMET.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GOLDOR_HELMET.getItem()));

        rec.setIngredient('J', new RecipeChoice.ExactChoice(SkyblockMaterial.GALACTICA_SINGULARITY.getItem()));
        return rec;
    }
}
