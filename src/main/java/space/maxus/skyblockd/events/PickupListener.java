package space.maxus.skyblockd.events;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;

import java.util.Objects;

public class PickupListener extends BetterListener {
    @EventHandler
    public void onPickup(EntityPickupItemEvent e){
        Item i = e.getItem();
        ItemStack s = i.getItemStack();
        boolean isSb = Objects.requireNonNull(s.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
        if(!isSb){
            CustomItem.toSkyblockItem(e.getItem());
        }
    }
}
