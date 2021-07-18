package space.maxus.skyblockd.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends BetterListener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if(e.getEntity().hasMetadata("NPC")) e.setDeathMessage(null);
    }
}
