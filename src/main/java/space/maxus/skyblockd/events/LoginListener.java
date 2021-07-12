package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.List;
import java.util.Objects;

public class LoginListener extends BetterListener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        processPlayer(p);
        if (p.hasPlayedBefore()) {
            p.sendMessage(ChatColor.YELLOW + "Welcome back to " + SkyblockD.getServerName() + " server, " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
            e.setJoinMessage(Objects.requireNonNull(p.getCustomName()).replace("&", "§").replace(" ", "") + ChatColor.GOLD + " just joined the server!");
        } else {
            p.sendMessage(ChatColor.GOLD + "Welcome to " + SkyblockD.getServerName() + " server, " + p.getName() + "!");
            e.setJoinMessage(ChatColor.GOLD + "New player, " + p.getName() +ChatColor.GOLD+ " just joined the server for first time!");
        }

    }

    private void processPlayer(Player p){
        List<RankContainer> r = UniversalHelper.filter(SkyblockD.playerRanks, c -> c.uuid.equals(p.getUniqueId().toString()));
        if(r.isEmpty()){
            RankContainer c = new RankContainer(p.getUniqueId().toString(), p.getName());
            SkyblockD.playerRanks.add(c);
            RankHelper.updateRanks();
            r = UniversalHelper.filter(SkyblockD.playerRanks, rc -> rc.uuid.equals(p.getUniqueId().toString()));
        }

        RankContainer cont = r.get(r.size()-1);
        String rank = cont.rankGroup;

        String rname = ((String) SkyblockD.getRankGroups().get(rank)).replace("&", "§");
        String name = rname + " " + p.getName();

        String stripped = ChatColor.stripColor(((String)SkyblockD.getRankGroups().get(rank)).replace("&", "§"));

        p.setDisplayName(name);
        p.setPlayerListName(name);
        p.setCustomName(name.replace(stripped, "").replace(" ", ""));

        p.setCustomNameVisible(true);
    }
}
