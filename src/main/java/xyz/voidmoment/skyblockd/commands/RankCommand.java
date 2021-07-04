package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import xyz.voidmoment.skyblockd.SkyblockD;
import xyz.voidmoment.skyblockd.helpers.RankHelper;
import xyz.voidmoment.skyblockd.objects.RankContainer;

import java.util.UUID;

public class RankCommand implements ChatCommand {

    @Override
    public String getName() {
        return "sbrank";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if(args.length > 0) {
                String name = args[0];
                Player p = SkyblockD.getHost().getPlayer(name);
                if(p == null) return false;
                String rank = SkyblockD.getRankGroups().containsKey(args[1]) ? args[1] : "rank.none";

                UUID id = p.getUniqueId();
                RankContainer container = new RankContainer(id.toString(), p.getName());
                container.rankGroup = rank;

                SkyblockD.playerRanks.put(id.toString(), container);

                RankHelper.updateRanks();

                p.setDisplayName(SkyblockD.getRankGroups().get(rank) + " "+ p.getName());
                return true;
            }
            return false;
        });
    }
}
