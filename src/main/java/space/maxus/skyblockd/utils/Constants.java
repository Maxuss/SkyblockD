package space.maxus.skyblockd.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class Constants {
    private final HashMap<Integer, ChatColor> damageMap;

    public HashMap<Integer, ChatColor> getDamageMap() {
        return damageMap;
    }

    public Constants() {
        damageMap = new HashMap<Integer, ChatColor>() {
            {
                put(0, ChatColor.WHITE);
            }

            {
                put(1, ChatColor.YELLOW);
            }

            {
                put(2, ChatColor.YELLOW);
            }

            {
                put(3, ChatColor.GOLD);
            }

            {
                put(4, ChatColor.RED);
            }

            {
                put(5, ChatColor.RED);
            }

            {
                put(6, ChatColor.GOLD);
            }

            {
                put(7, ChatColor.YELLOW);
            }

            {
                put(8, ChatColor.YELLOW);
            }

            {
                put(9, ChatColor.WHITE);
            }
        };
    }
}
