package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.ChatColor;

public enum SkyblockRarity {

    COMMON       ("COMMON", ChatColor.WHITE),
    UNCOMMON     ("UNCOMMON", ChatColor.GREEN),
    RARE         ("RARE", ChatColor.BLUE),
    EPIC         ("EPIC", ChatColor.DARK_PURPLE),
    LEGENDARY    ("LEGENDARY", ChatColor.GOLD),
    MYTHIC       ("MYTHIC", ChatColor.LIGHT_PURPLE),
    SUPREME      ("SUPREME", ChatColor.DARK_RED),
    SPECIAL      ("SPECIAL", ChatColor.RED),
    VERY_SPECIAL ("VERY SPECIAL", ChatColor.RED)
    ;

    public String displayName;
    public String unformattedName;
    public ChatColor displayColor;

    SkyblockRarity(String display, ChatColor color) {
        displayName = color + "" + ChatColor.BOLD + display;
        unformattedName = display;
        displayColor = color;
    }
}
