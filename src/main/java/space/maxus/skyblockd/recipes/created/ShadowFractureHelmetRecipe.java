package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ShadowFractureHelmetRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("shadow_fracture_helmet_recipe"), SkyblockMaterial.SHADOW_FRACTURE_HELMET.getItem());

        rec.shape(
                "OOO",
                "OGO",
                "ONO"
        );
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GEMSTONE_HELMET.getItem()));
        rec.setIngredient('N', Material.NETHERITE_HELMET);

        return rec;
    }
}
