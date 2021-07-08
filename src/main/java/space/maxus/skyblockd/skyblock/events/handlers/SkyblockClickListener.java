package space.maxus.skyblockd.skyblock.events.handlers;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import space.maxus.skyblockd.events.BetterListener;
import space.maxus.skyblockd.events.SkyblockItemClickEvent;

import java.util.Objects;

public class SkyblockClickListener extends BetterListener {
    @EventHandler
    public void onSkyblockClick(SkyblockItemClickEvent e){
        PlayerInteractEvent ev = (PlayerInteractEvent) e.getPredcessor();
        String n = Objects.requireNonNull(Objects.requireNonNull(ev.getItem()).getItemMeta()).getDisplayName();
        Bukkit.broadcastMessage(n);
    }
}
