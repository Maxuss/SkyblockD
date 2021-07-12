package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.ArmorSet;

public class ShadowFractureSet extends ArmorSet {
    @Override
    public String getSkyblockId() {
        return "set::SHADOW_FRACTURE";
    }

    @Override
    public ItemStack getHelmet() {
        return new ShadowFractureHelmet().generate();
    }

    @Override
    public ItemStack getChestplate() {
        return new ShadowFractureChestplate().generate();
    }

    @Override
    public ItemStack getLeggings() {
        return new ShadowFractureLeggings().generate();
    }

    @Override
    public ItemStack getBoots() {
        return new ShadowFractureBoots().generate();
    }
}
