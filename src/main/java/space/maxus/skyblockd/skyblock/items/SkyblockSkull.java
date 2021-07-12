package space.maxus.skyblockd.skyblock.items;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;

public abstract class SkyblockSkull extends SkyblockItem {

    public abstract SkyblockItemConfig getConfig();
    public abstract boolean hasGlint();
    public abstract String getSkyblockId();
    public abstract ItemStack postInit(ItemStack i);
    public abstract String getSkinHash();

    // works kinda the same
    @Override
    public ItemStack generate(){
        ItemStack sk = SkullCreator.itemFromBase64(getSkinHash());
        return inheritanceGeneration(sk);
    }
}
