package space.maxus.skyblockd.util;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public abstract class SkyblockPlayer implements Player {
    public void give(@NotNull SkyblockItem item){
        this.getInventory().addItem(item.generate());
    }

    public void give(@NotNull SkyblockMaterial mat){
        this.getInventory().addItem(mat.getItem());
    }
}
