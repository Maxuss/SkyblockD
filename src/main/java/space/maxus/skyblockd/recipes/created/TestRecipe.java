package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.created.RecombobulatorCore;
import space.maxus.skyblockd.skyblock.items.created.Storm;

public class TestRecipe extends RecipeBase<ShapedRecipe> {

    @Override
    public @NotNull ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(SkyblockD.getKey("test_recipe"), new Storm().generate());
        r.shape(" A ", " B ", " A ");
        r.setIngredient('A', Material.GOLD_INGOT);
        r.setIngredient('B', new RecipeChoice.ExactChoice(new RecombobulatorCore().generate()));
        return r;
    }
}
