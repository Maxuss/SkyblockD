package space.maxus.skyblockd.recipes.created;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeBase;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EnchantedDarkPrismarineRecipe extends RecipeBase<ShapedRecipe> {
    @Override
    public ShapedRecipe getRecipe() {
        ItemStack eink = SkyblockMaterial.ENCHANTED_INK_SAC.getItem();
        ItemStack eprism = SkyblockMaterial.ENCHANTED_PRISMARINE_SHARD.getItem();
        ItemStack ecryst = SkyblockMaterial.ENCHANTED_PRISMARINE_CRYSTALS.getItem();
        ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey("enchanted_dark_prism_recipe"), SkyblockMaterial.ENCHANTED_DARK_PRISMARINE.getItem());
        rec.shape(
                "IPI",
                "PCP",
                "IPI"
        );
        rec.setIngredient('P', new RecipeChoice.ExactChoice(eprism));
        rec.setIngredient('C', new RecipeChoice.ExactChoice(ecryst));
        rec.setIngredient('I', new RecipeChoice.ExactChoice(eink));
        return rec;
    }
}
