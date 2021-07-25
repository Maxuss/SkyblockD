package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class StrongSet extends ArmorSet {

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("strong_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.STRONG_DRAGON_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.STRONG_DRAGON_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.STRONG_DRAGON_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.STRONG_DRAGON_BOOTS.getItem();
    }
}
