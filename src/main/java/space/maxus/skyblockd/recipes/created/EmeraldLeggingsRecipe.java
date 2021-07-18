package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.LeggingsRecipe;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EmeraldLeggingsRecipe extends LeggingsRecipe {
    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public ItemStack getResult() {
        return SkyblockMaterial.EMERALD_LEGGINGS.getItem();
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("emerald_recipe_2");
    }
}
