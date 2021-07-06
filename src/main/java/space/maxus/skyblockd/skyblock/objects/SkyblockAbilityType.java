package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.ChatColor;

public enum SkyblockAbilityType {
    PASSIVE           (ChatColor.GOLD + "Item Ability: %NAME%"),
    RIGHT_CLICK       (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"RIGHT CLICK"),
    LEFT_CLICK        (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"LEFT CLICK"),
    FULL_SET_BONUS    (ChatColor.GOLD + "Full Set Bonus: %NAME%"),
    SNEAK             (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK"),
    SNEAK_RIGHT_CLICK (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK RIGHT CLICK"),
    SNEAK_LEFT_CLICK  (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"SNEAK LEFT CLICK"),
    DOUBLE_RIGHT_CLICK(ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"DOUBLE RIGHT CLICK"),
    DOUBLE_LEFT_CLICK (ChatColor.GOLD + "Item Ability: %NAME% "+ChatColor.YELLOW+""+ChatColor.BOLD+"DOUBLE LEFT CLICK")
    ;

    public String shown;

    SkyblockAbilityType(String display){
        shown = display;
    }
}
