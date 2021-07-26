package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ShortbowRecipe {
    public ShortbowRecipe(String name, SkyblockMaterial base) {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name+"_recipe"), SkyblockMaterial.valueOf(name).getItem());
        rec.shape(
                "IEG",
                "EBE",
                "GEI"
        );
        rec.setIngredient('I', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_GOLD_INGOT.getItem()));
        rec.setIngredient('E', new RecipeChoice.ExactChoice(base.getItem()));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.SHORTBOW_BASE.getItem()));
        SkyblockD.getHost().addRecipe(rec);
    }
}
