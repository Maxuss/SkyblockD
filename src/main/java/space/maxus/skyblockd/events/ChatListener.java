package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.Arrays;
import java.util.List;

public class ChatListener extends BetterListener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (SkyblockD.getCfg().ranksFancy() && SkyblockD.getCfg().ranksEnabled()) {
            Player p = e.getPlayer();
            RankContainer c = new RankContainer(p.getUniqueId().toString(), p.getName());
            List<RankContainer> filtered = UniversalHelper.filter(
                            SkyblockD.playerRanks,
                            container -> container.uuid.equals(p.getUniqueId().toString()));
            if (filtered.isEmpty()) {
                SkyblockD.playerRanks.add(c);
                RankHelper.updateRanks();
            }
            RankContainer cont;
            filtered = UniversalHelper
                    .filter(
                            SkyblockD.playerRanks,
                            container -> container.uuid.equals(p.getUniqueId().toString()));
            try {
                if(filtered.size() > 1) {
                    SkyblockD.playerRanks.remove(filtered.get(0));
                }
                cont = filtered.get(filtered.size() - 1);
            } catch (Exception ex) {
                SkyblockD.logger.warning(ex + " " + Arrays.toString(ex.getStackTrace()));
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
