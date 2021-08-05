package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;

public class MenuRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("sbmenu_recipe"), SkyblockD.getItemManager().generated.get("skyblockd:SKYBLOCK_MENU"));
        r.shape(
                " S ",
                "SDS",
                " S "
        );
        r.setIngredient('S', Material.STICK);
        r.setIngredient('D', Material.DIAMOND);
        return r;
    }
}
