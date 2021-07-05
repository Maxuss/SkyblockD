package xyz.voidmoment.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import static org.bukkit.event.Event.Result;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if(title.equalsIgnoreCase(ChatColor.DARK_GRAY+"SkyblockD Menu"))
        {
            e.setResult(Result.DENY);
            p.updateInventory();
        }
    }
}
