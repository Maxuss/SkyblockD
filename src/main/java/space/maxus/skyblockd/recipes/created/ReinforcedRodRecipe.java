package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ReinforcedRodRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("reinforced_rod_recipe"), SkyblockMaterial.REINFORCED_ROD.getItem());

        rec.shape(
                "III",
                "IRI",
                "III"
        );
        rec.setIngredient('I', Material.IRON_INGOT);
        rec.setIngredient('R', Material.FISHING_ROD);

        return rec;
    }
}
