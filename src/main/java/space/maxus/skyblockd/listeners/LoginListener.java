package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.List;
import java.util.Objects;

public class LoginListener extends BetterListener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(SkyblockD.inMaintenace) {
            if (p.hasPermission("skyblockd.admin")) {
                p.sendMessage(ChatColor.RED + "The server is currently in maintenance state");
            } else {
                p.kickPlayer(ChatColor.RED + "The server is currently in maintenance mode\n" +
                        ChatColor.GRAY + "Please wait for official updates!");
            }
        } else {
            processPlayer(p);
            if (p.hasPlayedBefore()) {
                p.sendMessage(ChatColor.YELLOW + "Welcome back to " + SkyblockD.getServerName() + " server, " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                e.setJoinMessage(Objects.requireNonNull(p.getCustomName()).replace("&", "§").replace(" ", "") + ChatColor.GOLD + " just joined the server!");
            } else {
                p.sendMessage(ChatColor.GOLD + "Welcome to " + SkyblockD.getServerName() + " server, " + p.getName() + "!");
                e.setJoinMessage(ChatColor.GOLD + "New player, " + p.getName() + ChatColor.GOLD + " just joined the server for first time!");
            }
        }
    }

    private void processPlayer(Player p){
        List<PlayerContainer> ps = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(p.getUniqueId()));

        if(ps.isEmpty()){
            RankContainer c = new RankContainer(p.getUniqueId().toString(), p.getName());
            PlayerSkills skills = PlayerSkills.EMPTY;
            PlayerContainer pl = new PlayerContainer(c, p.getUniqueId(), skills, p.hasPermission("skyblockd.admin"));
            SkyblockD.players.add(pl);
            ContainerHelper.updatePlayers();
            ps = UniversalHelper.filter(SkyblockD.getPlayers(), o -> o.uuid.equals(p.getUniqueId()));
        }

        PlayerContainer pc = ps.get(ps.size()-1);

        RankContainer pr = pc.ranks;

        String rank = pr.rankGroup;

        String rname = ChatColor.translateAlternateColorCodes('&', (String) SkyblockD.getRankGroups().get(rank));
        String name = rname + " " + p.getName();

        String stripped = ChatColor.stripColor(rname);

        p.setDisplayName(name);
        p.setPlayerListName(name);
        p.setCustomName(name.replace(stripped, "").replace(" ", ""));

        p.setCustomNameVisible(true);
    }
}
