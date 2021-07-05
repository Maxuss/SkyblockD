package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.objects.RankContainer;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if(SkyblockD.getCfg().ranksFancy() && SkyblockD.getCfg().ranksEnabled()) {
            Player p = e.getPlayer();
            if (!SkyblockD.playerRanks.containsKey(p.getUniqueId().toString())) {
                SkyblockD.playerRanks.put(p.getUniqueId().toString(), new RankContainer(p.getUniqueId().toString(), p.getName()));
                RankHelper.updateRanks();
            }
            RankContainer cont;
            try {
                cont = (RankContainer) SkyblockD.playerRanks.get(p.getUniqueId().toString());
            } catch (Exception ex) {
                cont = new RankContainer(p.getUniqueId().toString(), p.getName());
                cont.rankGroup = "rank.none";
            }
            String prefix = (String) SkyblockD.getRankGroups().get(cont.rankGroup);
            String full = (prefix.startsWith("B") ? prefix.replaceFirst("B", "") : prefix) + (cont.rankGroup.equals("rank.none") ? "" : " ") + p.getName();
            String m = full.replaceFirst("B", "") + ChatColor.WHITE + ": " + e.getMessage();
            e.setFormat(m.replace("&", "§"));
        }
    }
}
