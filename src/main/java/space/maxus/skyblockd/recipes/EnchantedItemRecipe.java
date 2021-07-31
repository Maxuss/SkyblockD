package space.maxus.skyblockd.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EnchantedItemRecipe {
    public EnchantedItemRecipe(@NotNull Material base) {
        try {
            ItemStack matItem = new ItemStack(base);
            CustomItem.toSkyblockItem(matItem);
            String enchName = "ENCHANTED_"+base.name();
            SkyblockMaterial result = SkyblockMaterial.valueOf(enchName);
            ItemStack res = result.getItem();
            ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(enchName+"_RECIPE"), res);
            rec.shape(
                    "III",
                    "III",
                    "III"
            );
            rec.setIngredient('I', new RecipeChoice.ExactChoice(matItem));
            SkyblockD.getHost().addRecipe(rec);
        } catch (Exception e) {
            SkyblockD.logger.severe("Could not load register enchanted item recipe!");
            SkyblockD.logger.severe(e.toString());
        }
    }

    public EnchantedItemRecipe(@NotNull ItemStack base, @NotNull SkyblockMaterial result) {
        try {
            ItemStack res = result.getItem();
            ShapedRecipe rec = new ShapedRecipe(SkyblockD.getKey(result.name()+"_RECIPE"), res);
            rec.shape(
                    " I ",
                    "III",
                    " I "
            );
            rec.setIngredient('I', new RecipeChoice.ExactChoice(base));
            SkyblockD.getHost().addRecipe(rec);
        } catch (Exception e) {
            SkyblockD.logger.severe("Could not load register enchanted item recipe!");
            SkyblockD.logger.severe(e.toString());
        }
    }
}
