package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.ScoreboardHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.PlayerSkills;
import space.maxus.skyblockd.objects.RankContainer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoginListener extends BetterListener {
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
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

    private void processPlayer(@NotNull Player p){
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
        pc.lastJoin = System.currentTimeMillis();
        RankContainer pr = pc.ranks;

        String rank = pr.rankGroup;

        String rname = ChatColor.translateAlternateColorCodes('&', (String) SkyblockD.getRankGroups().get(rank));
        String name = rname + " " + p.getName();

        String stripped = ChatColor.stripColor(rname);

        p.setDisplayName(name);
        p.setPlayerListName(name);
        p.setCustomName(name.replace(stripped, "").replace(" ", ""));

        p.setCustomNameVisible(true);

        ContainerHelper.updatePlayers();

        Scoreboard sb;
        Objective obj;
        if(ScoreboardHelper.playerBoards.containsKey(p.getUniqueId())) {
            sb = ScoreboardHelper.playerBoards.get(p.getUniqueId());
            obj = sb.getObjective("main");
        } else {
            sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
            obj = sb.registerNewObjective("main", "dummy", ChatColor.YELLOW+""+ChatColor.BOLD+"SkyblockD");
            ScoreboardHelper.playerBoards.put(p.getUniqueId(), sb);
        }
        assert obj != null;

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String strDate = sdf.format(date);

        Score sbdate = obj.getScore(ChatColor.GRAY+strDate);
        sbdate.setScore(0);
        Score empty = obj.getScore(" ");
        empty.setScore(1);
        Biome pb = p.getLocation().getBlock().getBiome();
        HashMap<Biome, String> a = MovementListener.importantBiomes;
        String loc = ChatColor.DARK_GRAY+"???";
        if(a.containsKey(pb)) {
            loc = a.get(pb);
        }
        Score location = obj.getScore(ChatColor.GRAY+"Location: "+loc);
        location.setScore(2);
        Score ee = obj.getScore("  ");
        ee.setScore(3);
        Score bottom = obj.getScore(ChatColor.AQUA+""+ChatColor.BOLD+SkyblockD.getServerIp());
        bottom.setScore(4);

        p.setScoreboard(sb);

    }
}
