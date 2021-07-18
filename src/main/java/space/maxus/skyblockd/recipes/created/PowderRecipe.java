package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class PowderRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("recipe_trans_powder"), SkyblockMaterial.TRANSMUTATION_POWDER.getItem());
        r.shape(
                " R ",
                "DSE",
                " L "
        );
        r.setIngredient('R', Material.REDSTONE_BLOCK);
        r.setIngredient('L', Material.LAPIS_BLOCK);
        r.setIngredient('S', Material.GUNPOWDER);
        r.setIngredient('D', Material.DIAMOND);
        r.setIngredient('E', Material.EMERALD);
        return r;
    }
}
