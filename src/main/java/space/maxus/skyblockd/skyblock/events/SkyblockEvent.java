package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class SkyblockEvent<T extends Event> extends Event {
    private static final HandlerList handlers = new HandlerList();

    public abstract T getPredcessor();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }


}
