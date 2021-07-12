package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

public abstract class ArmorSet implements SkyblockFeature {

    public abstract String getSkyblockId();

    public abstract ItemStack getHelmet();
    public abstract ItemStack getChestplate();
    public abstract ItemStack getLeggings();
    public abstract ItemStack getBoots();

    public ArmorSet(){ }

    public void register(){
        SkyblockD.getArmorSets().put(getSkyblockId(), this);
    }
}
