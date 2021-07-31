package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

public abstract class ArmorSet implements SkyblockFeature {

    public abstract @NotNull String getSkyblockId();

    public abstract ItemStack getHelmet();
    public abstract ItemStack getChestplate();
    public abstract ItemStack getLeggings();
    public abstract ItemStack getBoots();

    public void register(){
        SkyblockD.getArmorSets().put(getSkyblockId(), this);
    }
}
