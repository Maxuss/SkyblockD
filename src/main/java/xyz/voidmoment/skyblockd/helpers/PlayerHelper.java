package xyz.voidmoment.skyblockd.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHelper {
    public static String getUuid(String name){
        Player pl = Bukkit.getPlayer(name);
        if(pl == null) return null;
        return pl.getUniqueId().toString();
    }
}
