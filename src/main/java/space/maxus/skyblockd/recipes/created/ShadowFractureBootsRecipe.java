package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class ShadowFractureBootsRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("shadow_fracture_boots_recipe"), SkyblockMaterial.SHADOW_FRACTURE_BOOTS.getItem());

        rec.shape(
                "OOO",
                "OGO",
                "ONO"
        );
        rec.setIngredient('O', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_OBSIDIAN.getItem()));
        rec.setIngredient('G', new RecipeChoice.ExactChoice(SkyblockMaterial.GEMSTONE_BOOTS.getItem()));
        rec.setIngredient('N', Material.NETHERITE_BOOTS);

        return rec;
    }
}
