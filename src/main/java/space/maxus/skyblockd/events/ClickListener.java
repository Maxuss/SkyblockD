package space.maxus.skyblockd.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;

public class ClickListener extends BetterListener {
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        if(i != null){
            ItemMeta m = i.getItemMeta();
            assert m != null;
            boolean h = m.getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
            if(h) {
                SkyblockItemClickEvent event = new SkyblockItemClickEvent(e);
                SkyblockD.getPluginManager().callEvent(event);
            }
        }
    }
}
