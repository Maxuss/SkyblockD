package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ThanathosRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("thanathos_recipe"), SkyblockMaterial.THANATHOS.getItem());
        rec.shape(
                "NSN",
                "IAI",
                "NWN"
        );
        rec.setIngredient('N', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHERITE_SCRAP.getItem()));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHERITE.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHER_STAR.getItem()));
        rec.setIngredient('A', new RecipeChoice.ExactChoice(SkyblockMaterial.ASPECT_OF_THE_END.getItem()));
        return rec;
    }
}
