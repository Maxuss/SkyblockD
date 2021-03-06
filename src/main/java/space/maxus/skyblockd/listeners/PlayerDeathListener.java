package space.maxus.skyblockd.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.objects.BetterListener;

public class PlayerDeathListener extends BetterListener {
    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent e) {
        if(e.getEntity().hasMetadata("NPC")) e.setDeathMessage(null);
    }
}
