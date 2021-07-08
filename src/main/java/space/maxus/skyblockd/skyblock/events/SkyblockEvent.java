package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class SkyblockEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public abstract Event getPredcessor();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
