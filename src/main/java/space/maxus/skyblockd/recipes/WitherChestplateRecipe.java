package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherChestplateRecipe {
    public WitherChestplateRecipe(SkyblockMaterial frag, SkyblockMaterial drag, String name) {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name+"_chestplate_recipe"), SkyblockMaterial.valueOf(name.toUpperCase(Locale.ENGLISH)+"_CHESTPLATE").getItem());

        rec.shape(
                "BDB",
                "CFC",
                "CCC"
        );
        rec.setIngredient('B', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BONE_BLOCK.getItem()));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COAL_BLOCK.getItem()));
        rec.setIngredient('F', new RecipeChoice.ExactChoice(frag.getItem()));
        rec.setIngredient('D', new RecipeChoice.ExactChoice(drag.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
