package space.maxus.skyblockd.recipes;

import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class DragonSetRecipe {
    public DragonSetRecipe(@NotNull String name) {
        new DragonBootsRecipe(SkyblockMaterial.valueOf(name+"_FRAGMENT"), SkyblockMaterial.valueOf(name+"_DRAGON_BOOTS"), name.toLowerCase(Locale.ENGLISH)+"_boots_recipe");
        new DragonLeggingsRecipe(SkyblockMaterial.valueOf(name+"_FRAGMENT"), SkyblockMaterial.valueOf(name+"_DRAGON_LEGGINGS"), name.toLowerCase(Locale.ENGLISH)+"_leggings_recipe");
        new DragonChestplateRecipe(SkyblockMaterial.valueOf(name+"_FRAGMENT"), SkyblockMaterial.valueOf(name+"_DRAGON_CHESTPLATE"), name.toLowerCase(Locale.ENGLISH)+"_chestplate_recipe");
        new DragonHelmetRecipe(SkyblockMaterial.valueOf(name+"_FRAGMENT"), SkyblockMaterial.valueOf(name+"_DRAGON_HELMET"), name.toLowerCase(Locale.ENGLISH)+"_helmet_recipe");
    }
}
