package space.maxus.skyblockd.recipes.created;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.ChestplateRecipe;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class EmeraldChestplateRecipe extends ChestplateRecipe {
    @Override
    public Material getMaterial() {
        return Material.EMERALD;
    }

    @Override
    public ItemStack getResult() {
        return SkyblockMaterial.EMERALD_CHESTPLATE.getItem();
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("emerald_recipe_3");
    }
}
