package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class PrismarineDaggerRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("prismarine_dagger_recipe"), SkyblockMaterial.PRISMARINE_DAGGER.getItem());
        rec.shape(
                "PCP",
                "CSC",
                "PCP"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_PRISMARINE_SHARD.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_PRISMARINE_CRYSTALS.getItem()));
        rec.setIngredient('S', Material.DIAMOND_SWORD);
        return rec;
    }
}
