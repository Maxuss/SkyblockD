package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

public class SkyblockItemClickEvent extends SkyblockEvent {

    private final PlayerInteractEvent predcessor;

    @Override
    public Event getPredcessor() {
        return predcessor;
    }

    public SkyblockItemClickEvent(PlayerInteractEvent e){
        predcessor = e;
    }
}
