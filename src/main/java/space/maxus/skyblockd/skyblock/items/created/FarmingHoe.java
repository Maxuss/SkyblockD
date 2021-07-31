package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.objects.SkyblockAbilityType;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface FarmingHoe {
    default List<SkyblockItemAbility> addFarmingAbility(int level, String name) {
        int amount = 5 * level;
        SkyblockItemAbility abil = new SkyblockItemAbility(name,
                SkyblockAbilityType.PASSIVE, Arrays.asList(
                        ChatColor.GRAY+"This hoe grants you",
                ChatColor.GOLD+"+"+amount+" "+ SkyblockConstants.FORTUNE+" Farming Fortune",
                ChatColor.GRAY+"when held!"
        ));
        return Collections.singletonList(abil);
    }
}