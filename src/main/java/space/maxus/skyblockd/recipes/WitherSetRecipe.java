package space.maxus.skyblockd.recipes;

import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Locale;

public class WitherSetRecipe {
    public WitherSetRecipe(@NotNull String name) {
        String upper = name.toUpperCase(Locale.ENGLISH);
        SkyblockMaterial frag = SkyblockMaterial.valueOf(upper+"_SOUL_FRAGMENT");
        new WitherBootsRecipe(frag, upper);
        new WitherLeggingsRecipe(frag, upper);
        new WitherChestplateRecipe(frag, upper);
        new WitherHelmetRecipe(frag, upper);
    }
}
