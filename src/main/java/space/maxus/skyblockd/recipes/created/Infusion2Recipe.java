package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class Infusion2Recipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("recipe_infusion_2"), SkyblockMaterial.CRYSTAL_INFUSION.getItem());
        r.shape(
                "DED",
                "EQE",
                "DED"
        );
        r.setIngredient('D', Material.DIAMOND);
        r.setIngredient('E', Material.EMERALD);
        r.setIngredient('Q', new RecipeChoice.ExactChoice(SkyblockMaterial.QUARTZ_INFUSION.getItem()));
        return r;
    }
}
