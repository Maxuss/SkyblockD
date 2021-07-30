package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class GoldorSet extends ArmorSet {
    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("goldor_set");
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.GOLDOR_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.GOLDOR_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.GOLDOR_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.GOLDOR_BOOTS.getItem();
    }
}
