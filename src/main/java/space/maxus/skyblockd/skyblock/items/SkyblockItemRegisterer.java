package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.utility.SkyblockRegisterer;

public class SkyblockItemRegisterer extends SkyblockRegisterer<SkyblockItem, ItemStack> {
    @Override
    public void register(SkyblockItem registrant) {
        contains.put(registrant.getSkyblockId(), registrant.getItem());
    }
}
