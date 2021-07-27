package space.maxus.skyblockd.recipes;

import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import javax.annotation.Nullable;

public class SimpleSetRecipe {
    public SimpleSetRecipe(String name, SkyblockMaterial base, @Nullable String pre) {
        ShapedRecipe h, c, l, b;
        if(pre == null) {
            h = new ShapedRecipe(SkyblockD.getKey(name+"_helmet_recipe"), SkyblockMaterial.valueOf(name+"_HELMET").getItem());
            h.shape(
                    "III",
                    "I I"
            );
            h.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            c = new ShapedRecipe(SkyblockD.getKey(name+"_chestplate_recipe"), SkyblockMaterial.valueOf(name+"_CHESTPLATE").getItem());
            c.shape(
                    "I I",
                    "III",
                    "III"
            );
            c.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            l = new ShapedRecipe(SkyblockD.getKey(name+"_leggings_recipe"), SkyblockMaterial.valueOf(name+"_LEGGINGS").getItem());
            l.shape(
                    "III",
                    "I I",
                    "I I"
            );
            l.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            b = new ShapedRecipe(SkyblockD.getKey(name+"_boots_recipe"), SkyblockMaterial.valueOf(name+"_BOOTS").getItem());
            b.shape(
                    "I I",
                    "I I"
            );
            b.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
        } else {
            h = new ShapedRecipe(SkyblockD.getKey(name+"_helmet_recipe"), SkyblockMaterial.valueOf(name+"_HELMET").getItem());
            h.shape(
                    "III",
                    "IPI"
            );
            h.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            h.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.valueOf(pre+"_HELMET").getItem()));
            c = new ShapedRecipe(SkyblockD.getKey(name+"_chestplate_recipe"), SkyblockMaterial.valueOf(name+"_CHESTPLATE").getItem());
            c.shape(
                    "IPI",
                    "III",
                    "III"
            );
            c.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            c.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.valueOf(pre+"_CHESTPLATE").getItem()));
            l = new ShapedRecipe(SkyblockD.getKey(name+"_leggings_recipe"), SkyblockMaterial.valueOf(name+"_LEGGINGS").getItem());
            l.shape(
                    "III",
                    "IPI",
                    "I I"
            );
            l.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            l.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.valueOf(pre+"_LEGGINGS").getItem()));
            b = new ShapedRecipe(SkyblockD.getKey(name+"_boots_recipe"), SkyblockMaterial.valueOf(name+"_BOOTS").getItem());
            b.shape(
                    "IPI",
                    "I I"
            );
            b.setIngredient('I', new RecipeChoice.ExactChoice(base.getItem()));
            b.setIngredient('P', new RecipeChoice.ExactChoice(SkyblockMaterial.valueOf(pre+"_BOOTS").getItem()));
        }
        SkyblockD.getHost().addRecipe(h);
        SkyblockD.getHost().addRecipe(c);
        SkyblockD.getHost().addRecipe(l);
        SkyblockD.getHost().addRecipe(b);
    }
}
