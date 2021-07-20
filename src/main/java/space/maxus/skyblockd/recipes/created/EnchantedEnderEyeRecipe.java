package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EnchantedEnderEyeRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ItemStack epowder = SkyblockMaterial.ENCHANTED_BLAZE_POWDER.getItem();
        ItemStack epearl = SkyblockMaterial.ENCHANTED_ENDER_PEARL.getItem();
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("enchanted_ender_eye_recipe"), SkyblockMaterial.ENCHANTED_EYE_OF_ENDER.getItem());
        rec.shape(
                "PBP",
                "BPB",
                "PBP"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(epearl));
        rec.setIngredient('B', new RecipeChoice.ExactChoice(epowder));
        return rec;
    }
}
