package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.Recipe;
import space.maxus.skyblockd.SkyblockD;

import java.util.Arrays;
import java.util.Objects;

public abstract class RecipeBase<R extends Recipe> {

    public abstract R getRecipe();

    public RecipeBase(){
        try {
            R recipe = getRecipe();
            SkyblockD.getHost().addRecipe(recipe);
            SkyblockD.logger.info("Successfully registered recipe for item " + Objects.requireNonNull(recipe.getResult().getItemMeta()).getDisplayName());
        } catch(Exception e){
            SkyblockD.logger.severe(e.toString());
            SkyblockD.logger.severe("Could not load recipe! Error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
