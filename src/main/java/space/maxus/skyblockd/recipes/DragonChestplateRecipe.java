package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class DragonChestplateRecipe {
    public DragonChestplateRecipe(SkyblockMaterial base, SkyblockMaterial result, String name) {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name), result.getItem());
        rec.shape(
                "P P",
                "PFP",
                "OOO"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_ENDER_PEARL.getItem()));
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(base.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
