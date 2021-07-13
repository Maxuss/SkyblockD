package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

public class SkyblockBlockBreakEvent extends SkyblockEvent {
    private final BlockBreakEvent predcessor;

    @Override
    public Event getPredcessor() {
        return predcessor;
    }

    public SkyblockBlockBreakEvent(BlockBreakEvent e){
        predcessor = e;
    }
}
