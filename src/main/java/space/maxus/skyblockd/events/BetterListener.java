package space.maxus.skyblockd.events;

import org.bukkit.event.Listener;
import space.maxus.skyblockd.SkyblockD;

public abstract class BetterListener implements Listener{

    public BetterListener(){
        SkyblockD.getPluginManager().registerEvents(this, SkyblockD.getInstance());
    }
}
