package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class DowngradeBladeRecipe {
    public DowngradeBladeRecipe(@NotNull SkyblockMaterial mat) {
        ShapelessRecipe rec = new ShapelessRecipe(SkyblockD.getKey(mat.name()+"_downgrade"), SkyblockMaterial.UNREFINED_BLADE.getItem());
        rec.addIngredient(new RecipeChoice.ExactChoice(mat.getItem()));

        SkyblockD.getHost().addRecipe(rec);
    }
}
