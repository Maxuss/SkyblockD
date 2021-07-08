package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import space.maxus.skyblockd.SkyblockD;

public class LoginListener extends BetterListener {
    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) {
            p.sendMessage(ChatColor.YELLOW + "Welcome back to " + SkyblockD.getServerName() + " server, " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
        } else {
            p.sendMessage(ChatColor.GOLD + "Welcome to " + SkyblockD.getServerName() + " server, " + p.getName() + "!");
            Bukkit.broadcastMessage(ChatColor.GOLD + "New player, " + p.getName() + " just joined the server for first time!");
        }
    }
}
