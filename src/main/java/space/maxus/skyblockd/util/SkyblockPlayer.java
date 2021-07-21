package space.maxus.skyblockd.util;

import org.bukkit.entity.Player;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

public abstract class SkyblockPlayer implements Player {
    public void give(SkyblockItem item){
        this.getInventory().addItem(item.generate());
    }

    public void give(SkyblockMaterial mat){
        this.getInventory().addItem(mat.getItem());
    }
}
