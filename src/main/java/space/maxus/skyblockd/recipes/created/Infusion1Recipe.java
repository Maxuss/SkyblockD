package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class Infusion1Recipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("recipe_infusion_1"), SkyblockMaterial.QUARTZ_INFUSION.getItem());
        r.shape(
                "QQQ",
                "QGQ",
                "QQQ"
        );
        r.setIngredient('Q', Material.QUARTZ);
        r.setIngredient('G', Material.GOLD_NUGGET);
        return r;
    }
}
