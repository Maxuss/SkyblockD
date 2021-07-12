package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.RankHelper;
import space.maxus.skyblockd.objects.RankContainer;

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

                if(!SkyblockD.playerRanks.contains(container)) SkyblockD.playerRanks.add(container);

                RankHelper.updateRanks();

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
