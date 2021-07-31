package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class KasmirFuryRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("kasmir_fury_recipe"), SkyblockMaterial.KASMIR_FURY.getItem());

        rec.shape(
                "WKW",
                "KDK",
                "WKW"
        );

        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('K', new RecipeChoice.ExactChoice(SkyblockMaterial.KASMIR_SOUL_FRAGMENT.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRACONIC_SHORTBOW.getItem()));

        return rec;
    }
}
