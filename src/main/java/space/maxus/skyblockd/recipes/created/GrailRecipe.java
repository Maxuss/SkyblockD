package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class GrailRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("recipe_holy_grail"), SkyblockMaterial.HOLY_GRAIL.getItem());
        r.shape(
                " W ",
                "HGC",
                " S "
        );
        r.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.GRAIL_1.getItem()));
        r.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GRAIL_3.getItem()));
        r.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.GRAIL_4.getItem()));
        r.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.GRAIL_2.getItem()));
        r.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        return r;
    }
}
