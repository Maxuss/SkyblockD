package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class SupremeStewRecipe extends RecipeBase<ShapelessRecipe> {
    @Override
    public ShapelessRecipe getRecipe() {
        ShapelessRecipe rec = new ShapelessRecipe(SkyblockD.getKey("supreme_stew_recipe"), SkyblockMaterial.SUPREME_STEW.getItem());

        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_PORKCHOP.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_CHICKEN.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_RABBIT.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BEEF.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_RABBIT_FOOT.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_NETHER_WART.getItem()));
        rec.addIngredient(new RecipeChoice.ExactChoice(SkyblockMaterial.ENCHANTED_BROWN_MUSHROOM.getItem()));
        rec.addIngredient(Material.BOWL);
        return rec;
    }
}
