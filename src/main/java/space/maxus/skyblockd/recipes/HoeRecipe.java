package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class HoeRecipe {
    public HoeRecipe(String name, String blueprint, String choice) {
        SkyblockMaterial hoe = SkyblockMaterial.valueOf(name+"_HOE");
        SkyblockMaterial bp = SkyblockMaterial.valueOf(blueprint+"_HOE_BLUEPRINT");
        SkyblockMaterial item = SkyblockMaterial.valueOf(choice);

        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(name+"_hoe_recipe"), hoe.getItem());
        rec.shape(
                "IPI",
                "PHP",
                "IPI"
        );
        rec.setIngredient('I', new RecipeChoice.ExactChoice(item.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(bp.getItem()));
        rec.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_POTATO.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
