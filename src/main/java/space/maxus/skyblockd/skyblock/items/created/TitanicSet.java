package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public class TitanicSet extends ArmorSet {
    @Override
    public String getSkyblockId() {
        return "";
    }

    @Override
    public ItemStack getHelmet() {
        return SkyblockMaterial.TITAN_HELMET.getItem();
    }

    @Override
    public ItemStack getChestplate() {
        return SkyblockMaterial.TITAN_CHESTPLATE.getItem();
    }

    @Override
    public ItemStack getLeggings() {
        return SkyblockMaterial.TITAN_LEGGINGS.getItem();
    }

    @Override
    public ItemStack getBoots() {
        return SkyblockMaterial.TITAN_BOOTS.getItem();
    }
}
