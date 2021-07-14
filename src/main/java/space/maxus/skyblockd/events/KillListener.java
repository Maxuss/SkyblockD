package space.maxus.skyblockd.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillListener extends BetterListener {
    @EventHandler
    public void onKill(EntityDeathEvent e) {
        LivingEntity en = e.getEntity();
        // TODO: Finish stuff for combat
    }
}
