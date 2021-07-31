package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.utility.SkyblockRegisterer;

public class SkyblockItemRegisterer extends SkyblockRegisterer<SkyblockItem, ItemStack> {
    @Override
    public void register(@NotNull SkyblockItem registrant) {
        contains.put(registrant.getSkyblockId(), registrant.getItem());
    }
}
