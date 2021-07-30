package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.objects.SkyblockAbilityType;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface WitherPiece {
    default List<SkyblockItemAbility> getAbility() {
        return Collections.singletonList(new SkyblockItemAbility("Withering Hate",
                SkyblockAbilityType.FULL_SET_BONUS,
                Arrays.asList(
                        ChatColor.GRAY+"Take "+ChatColor.RED+"20%"+ChatColor.GRAY+" less damage from",
                        ChatColor.GRAY+"withers, and be able to break part",
                        ChatColor.GRAY+"of their defence!"
                )));
    }
}
