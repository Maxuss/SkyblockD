package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.Arrays;
import java.util.List;

public class ChatListener extends BetterListener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (SkyblockD.getCfg().ranksFancy() && SkyblockD.getCfg().ranksEnabled()) {
            Player p = e.getPlayer();
            RankContainer c = new RankContainer(p.getUniqueId().toString(), p.getName());

            List<PlayerContainer> filtered = UniversalHelper.filter(
                            SkyblockD.players,
                            co -> co.uuid.equals(p.getUniqueId()));

            if (filtered.isEmpty()) {
                PlayerContainer pc = new PlayerContainer(c, p.getUniqueId(), PlayerSkills.EMPTY, p.hasPermission("skyblockd.admin"));

                SkyblockD.players.add(pc);
                ContainerHelper.updatePlayers();
            }
            RankContainer cont;
            filtered = UniversalHelper
                    .filter(
                            SkyblockD.players,
                            container -> container.uuid.equals(p.getUniqueId()));
            try {
                if(filtered.size() > 1) {
                    SkyblockD.players.remove(filtered.get(0));
                }
                cont = filtered.get(filtered.size() - 1).ranks;
                ContainerHelper.updatePlayers();
            } catch (Exception ex) {
                SkyblockD.logger.warning(ex + " " + Arrays.toString(ex.getStackTrace()));
                cont = new RankContainer(p.getUniqueId().toString(), p.getName());
                cont.rankGroup = "rank.none";
            }

            String prefix = (String) SkyblockD.getRankGroups().get(cont.rankGroup);
            String full = (prefix.startsWith("B") ? prefix.replaceFirst("B", "") : prefix) + (cont.rankGroup.equals("rank.none") ? "" : " ");
            String m = full.replace("B", "") +p.getName() + ChatColor.WHITE + ": " + e.getMessage();
            e.setFormat(m.replace("&", "§"));
        }
    }
}
