package space.maxus.skyblockd.discord;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;

import java.awt.*;

public class DiscordListener extends BetterListener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Color col = getColorFromPlayer(e.getPlayer());
        SkyblockD.getDiscord().sendEmbedFromPlayer(e.getPlayer(), message, false, col);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        String message = e.getPlayer().getName() + " has joined the server!";

        SkyblockD.getDiscord().sendEmbed(message, getColorFromPlayer(e.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent e) {
        String message = e.getPlayer().getName() + " has left the server!";

        SkyblockD.getDiscord().sendEmbed(message, getColorFromPlayer(e.getPlayer()));
    }

    @EventHandler()
    public void onDeath(PlayerDeathEvent e) {
        String name = e.getEntity().getName();
        Color color = getColorFromPlayer(e.getEntity());

        String message = e.getDeathMessage() == null ? name + " died." : e.getDeathMessage();

        SkyblockD.getDiscord().sendEmbedFromPlayer(e.getEntity(), message, true, color);
    }

    public static Color getColorFromPlayer(Player player) {
        PlayerContainer c = ContainerHelper.getPlayer(player);
        switch(c.ranks.rankGroup) {
            case "server.admin":
            case "server.owner":
                return ChatColor.RED.getColor();

            case "server.mod":
                return ChatColor.DARK_GREEN.getColor();

            case "server.jrhelper":
            case "server.helper":
                return ChatColor.BLUE.getColor();

            case "rank.mvp":
            case "rank.mvp.p":
                return ChatColor.AQUA.getColor();

            case "rank.vip":
            case "rank.vip.p":
                return ChatColor.GREEN.getColor();

            case "rank.mvp.pp":
                return ChatColor.GOLD.getColor();

            default: return ChatColor.GRAY.getColor();
        }
    }
}
