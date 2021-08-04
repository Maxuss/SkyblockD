package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SwiftDaggerRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("swift_dagger_recipe"), SkyblockMaterial.SWIFT_DAGGER.getItem());

        rec.shape(
                "I",
                "I",
                "S"
        );
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem()));
        rec.setIngredient('S', Material.STICK);

        return rec;
    }
}
