package space.maxus.skyblockd.helpers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardHelper {
    private static final @NotNull Scoreboard mainScoreboard;
    private static final @NotNull Objective skyblockObjective;
    public static final @NotNull HashMap<UUID, Scoreboard> playerBoards;

    public static @NotNull Scoreboard getMainScoreboard() {
        return mainScoreboard;
    }

    public static @NotNull Objective getSkyblockObjective() {
        return skyblockObjective;
    }

    static {
        mainScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        skyblockObjective = mainScoreboard.registerNewObjective("main", "dummy", ChatColor.YELLOW+""+ChatColor.BOLD+"Skyblock");
        playerBoards = new HashMap<>();
    }
}
