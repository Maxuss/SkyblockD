package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.Recipe;
import space.maxus.skyblockd.SkyblockD;

import java.util.Arrays;

public abstract class RecipeBase<R extends Recipe> {

    public abstract R getRecipe();

    public RecipeBase(){
        try {
            R recipe = getRecipe();
            SkyblockD.getHost().addRecipe(recipe);
            SkyblockD.logger.fine("Successfully registered recipe " + recipe.toString());
        } catch(Exception e){
            SkyblockD.logger.severe("Could not load recipe! Error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
