package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherHelmetRecipe {
    public WitherHelmetRecipe(@NotNull SkyblockMaterial frag, @NotNull String name) {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name+"_helmet_recipe"), SkyblockMaterial.valueOf(name.toUpperCase(Locale.ENGLISH)+"_HELMET").getItem());

        rec.shape(
                "BFB",
                "FWF",
                "CFC"
        );
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BONE_BLOCK.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(frag.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_HELMET.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
