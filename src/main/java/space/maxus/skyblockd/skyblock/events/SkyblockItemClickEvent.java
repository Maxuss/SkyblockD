package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.player.PlayerInteractEvent;

public class SkyblockItemClickEvent extends SkyblockEvent<PlayerInteractEvent> {

    private final PlayerInteractEvent predcessor;

    @Override
    public PlayerInteractEvent getPredcessor() {
        return predcessor;
    }

    public SkyblockItemClickEvent(PlayerInteractEvent e){
        predcessor = e;
    }
}
