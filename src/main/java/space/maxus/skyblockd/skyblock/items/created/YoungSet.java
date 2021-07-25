package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class YoungSet extends ArmorSet {

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("young_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.YOUNG_DRAGON_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.YOUNG_DRAGON_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.YOUNG_DRAGON_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.YOUNG_DRAGON_BOOTS.getItem();
    }
}
