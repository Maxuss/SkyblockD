package space.maxus.skyblockd.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerHelper {
    public static @Nullable String getUuid(@NotNull String name) {
        Player pl = Bukkit.getPlayer(name);
        if (pl == null) return null;
        return pl.getUniqueId().toString();
    }
}
