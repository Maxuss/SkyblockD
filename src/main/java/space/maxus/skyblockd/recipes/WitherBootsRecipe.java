package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherBootsRecipe {
    public WitherBootsRecipe(@NotNull SkyblockMaterial frag, @NotNull SkyblockMaterial drag, @NotNull String name) {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name+"_boots_recipe"), SkyblockMaterial.valueOf(name.toUpperCase(Locale.ENGLISH)+"_BOOTS").getItem());

        rec.shape(
                "BFB",
                "CDC"
        );
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BONE_BLOCK.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(frag.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(drag.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
