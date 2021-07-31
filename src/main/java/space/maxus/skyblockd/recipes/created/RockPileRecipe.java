package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class RockPileRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("enchanted_rock_pile_recipe"), SkyblockMaterial.ENCHANTED_ROCK_PILE.getItem());
        r.shape(
                "QDQ",
                "ACG",
                "QDQ"
        );
        r.setIngredient('Q', Material.QUARTZ);
        r.setIngredient('D', Material.DIORITE);
        r.setIngredient('G', Material.GRANITE);
        r.setIngredient('A', Material.ANDESITE);
        r.setIngredient('C', new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_COBBLESTONE.getItem()));
        return r;
    }
}
