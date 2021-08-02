package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.objects.SkyblockAbilityType;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface ErumdirPiece {
    default List<SkyblockItemAbility> getAbility() {
        SkyblockItemAbility abil = new SkyblockItemAbility(
                "End Keeper", SkyblockAbilityType.FULL_SET_BONUS,
                Arrays.asList(
                        ChatColor.GRAY+"Take "+ChatColor.GREEN+"20%"+ChatColor.GRAY+" less damage",
                        ChatColor.GRAY+"from all "+ChatColor.DARK_PURPLE+"Ender"+ChatColor.GRAY+" mobs!",
                        " ",
                        ChatColor.GRAY+"Boosts all your stats by "+ChatColor.GREEN+"15%",
                        ChatColor.GRAY+"while in "+ChatColor.DARK_PURPLE+"The End"
                )
        );
        return Collections.singletonList(abil);
    }
}
