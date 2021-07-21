package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CommandInfo(name = "sbrank", permission = "skyblockd.admin", playerOnly = false, configReq = "skyblockd.chat.ranks.enabled")
public class RankCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
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

            String rname = ChatColor.translateAlternateColorCodes('&', (String) SkyblockD.getRankGroups().get(rank));
            String _name = rname + " " + p.getName();

            String stripped = ChatColor.stripColor(rname);

            p.setDisplayName(_name);
            p.setPlayerListName(_name);
            p.setCustomName(_name.replace(stripped, ""));

            p.setCustomNameVisible(true);
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        if(args.length >= 1) {
            return new ArrayList<>(SkyblockD.getRankGroups().keySet());
        }
        return SkyblockD.getHost().getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
