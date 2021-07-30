package space.maxus.skyblockd.recipes;

import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherSetRecipe {
    public WitherSetRecipe(String name, String dragName) {
        String upper = name.toUpperCase(Locale.ENGLISH);
        String upperDrag = dragName.toUpperCase(Locale.ENGLISH);
        SkyblockMaterial frag = SkyblockMaterial.valueOf(upper+"_SOUL_FRAGMENT");
        new WitherBootsRecipe(frag, SkyblockMaterial.valueOf(upperDrag+"_DRAGON_BOOTS"), upper);
        new WitherLeggingsRecipe(frag, SkyblockMaterial.valueOf(upperDrag+"_DRAGON_LEGGINGS"), upper);
        new WitherChestplateRecipe(frag, SkyblockMaterial.valueOf(upperDrag+"_DRAGON_CHESTPLATE"), upper);
        new WitherHelmetRecipe(frag, SkyblockMaterial.valueOf(upperDrag+"_DRAGON_HELMET"), upper);
    }
}
