package space.maxus.skyblockd.events;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.items.CustomItem;

import java.util.Objects;

public class PickupListener extends BetterListener {
    @EventHandler
    public void onPickup(EntityPickupItemEvent e){
        // save a lot of memory by checking if entity is player
        // if it isnt, simply dont update item until player picks it up
        if(e.getEntity() instanceof Player) {
            Item i = e.getItem();
            ItemStack s = i.getItemStack();
            boolean isSb = Objects.requireNonNull(s.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
            if (!isSb) CustomItem.toSkyblockItem(e.getItem());

            Player p = (Player) e.getEntity();

            ItemHelper.usePress(p, s);
        }
    }

    @EventHandler
    public void onInventoryUpdate(InventoryOpenEvent e){
        Inventory i = e.getInventory();
        ItemStack[] items = i.getContents();
        for (ItemStack item : items) {
            if(item != null && item.getItemMeta() != null) {
                boolean isSb = item.getItemMeta().getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
                if (!isSb) CustomItem.toSkyblockItem(item);
            }
        }
    }
}
