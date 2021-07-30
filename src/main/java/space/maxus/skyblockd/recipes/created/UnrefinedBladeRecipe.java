package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class UnrefinedBladeRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("unrefined_blade_recipe"), SkyblockMaterial.UNREFINED_BLADE.getItem());

        rec.shape(
                "SDS",
                "WAW",
                "NHN"
        );
        rec.setIngredient('D', new RecipeChoice.ExactChoice(SkyblockMaterial.DEMETER.getItem()));
        rec.setIngredient('A', new RecipeChoice.ExactChoice(SkyblockMaterial.ASPECT_OF_DRAGON.getItem()));
        rec.setIngredient('H', new RecipeChoice.ExactChoice(SkyblockMaterial.ENERGETIC_HANDLE.getItem()));
        rec.setIngredient('W', new RecipeChoice.ExactChoice(SkyblockMaterial.WITHER_JAR.getItem()));
        rec.setIngredient('N', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHERITE.getItem()));
        rec.setIngredient('S', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHER_STAR.getItem()));

        return rec;
    }
}
