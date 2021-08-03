package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class HeroRodRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("hero_rod_recipe"), SkyblockMaterial.HERO_ROD.getItem());

        rec.shape(
                "GGG",
                "ERE",
                "GGG"
        );
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_HONEYCOMB_BLOCK.getItem()));
        rec.setIngredient('R', new RecipeChoice.ExactChoice(SkyblockMaterial.DARK_MATTER_ROD.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.GALACTICA_SINGULARITY.getItem()));
        return rec;
    }
}
