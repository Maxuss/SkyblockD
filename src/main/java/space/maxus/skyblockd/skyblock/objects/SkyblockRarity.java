package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum SkyblockRarity {
    ERROR        (ChatColor.MAGIC+"ERROR", ChatColor.YELLOW, 0),
    COMMON       ("COMMON", ChatColor.WHITE, 1),
    UNCOMMON     ("UNCOMMON", ChatColor.GREEN, 2),
    RARE         ("RARE", ChatColor.BLUE, 3),
    EPIC         ("EPIC", ChatColor.DARK_PURPLE, 4),
    LEGENDARY    ("LEGENDARY", ChatColor.GOLD, 5),
    RELIC        ("RELIC", ChatColor.GOLD, 6),
    MYTHIC       ("MYTHIC", ChatColor.LIGHT_PURPLE, 7),
    SUPREME      ("SUPREME", ChatColor.DARK_RED, 8),
    SPECIAL      ("SPECIAL", ChatColor.RED, 9),
    VERY_SPECIAL ("VERY SPECIAL", ChatColor.RED, 10)
    ;
    private final int index;

    public final @NotNull String displayName;
    public final String unformattedName;
    public final ChatColor displayColor;


    SkyblockRarity(String display, ChatColor color, int index) {
        displayName = color + "" + ChatColor.BOLD + display;
        unformattedName = display;
        displayColor = color;
        this.index = index;
    }

    public int getIndex() {return index;}

    public static SkyblockRarity byIndex(int index){
        if(index >= values().length || index < 0) return VERY_SPECIAL;
        return values()[index];
    }
}
