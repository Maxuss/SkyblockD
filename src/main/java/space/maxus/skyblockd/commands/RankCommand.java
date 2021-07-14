package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.List;
import java.util.UUID;

public class RankCommand implements ChatCommand {

    @Override
    public String getName() {
        return "sbrank";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (args.length > 0) {
                String name = args[0];
                Player p = SkyblockD.getHost().getPlayer(name);
                if (p == null) return false;
                String rank = SkyblockD.getRankGroups().containsKey(args[1]) ? args[1] : "rank.none";

                UUID id = p.getUniqueId();
                RankContainer container = new RankContainer(id.toString(), p.getName());
                container.rankGroup = rank;

                List<PlayerContainer> players = UniversalHelper.filter(SkyblockD.players, pc -> pc.uuid.toString().equals(p.getUniqueId().toString()));

                if(players.isEmpty()) {
                    PlayerContainer cont = new PlayerContainer(container, p.getUniqueId(), PlayerSkills.EMPTY, p.hasPermission("skyblockd.admin"));
                    SkyblockD.players.add(cont);
                } else {
                    PlayerContainer nc = players.get(players.size()-1);
                    nc.ranks = container;
                    SkyblockD.players.add(nc);
                }

                ContainerHelper.updatePlayers();

                String rname = ((String) SkyblockD.getRankGroups().get(rank)).replace("&", "§");
                String _name = rname + " " + p.getName();

                String stripped = ChatColor.stripColor(((String)SkyblockD.getRankGroups().get(rank)).replace("&", "§"));

                p.setDisplayName(_name);
                p.setPlayerListName(_name);
                p.setCustomName(_name.replace(stripped, ""));

                p.setCustomNameVisible(true);
                return true;
            }
            return false;
        });
    }
}
