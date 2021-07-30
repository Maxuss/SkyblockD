package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherBladeRecipe {
    public WitherBladeRecipe(String witherName, String swordName) {
        String upper = swordName.toUpperCase(Locale.ENGLISH);

        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(upper+"_recipe"), SkyblockMaterial.valueOf(upper).getItem());

        rec.shape(
                "ESE",
                "CBC",
                "EHE"
        );
        rec.setIngredient('E', new RecipeChoice.ExactChoice(SkyblockMaterial.SHADED_EYE.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENERGETIC_HANDLE.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.UNREFINED_BLADE.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.valueOf(witherName+"_SOUL_FRAGMENT").getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
