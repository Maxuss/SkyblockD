package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class WorldDigesterRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("world_digester_recipe"), SkyblockMaterial.WORLD_DIGESTER.getItem());

        rec.shape(
                "QDQ",
                " H ",
                " H "
        );
        rec.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_QUARTZ_BLOCK.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DRAGON_SINGULARITY.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENERGETIC_HANDLE.getItem()));

        return rec;
    }
}
