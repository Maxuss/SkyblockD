package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.LeggingsRecipe;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class LilyLeggingsRecipe extends LeggingsRecipe {
    @Override
    public Material getMaterial() {
        return Material.LILY_PAD;
    }

    @Override
    public ItemStack getResult() {
        return SkyblockMaterial.LILY_LEGGINGS.getItem();
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("lily_leggings_recipe");
    }
}
