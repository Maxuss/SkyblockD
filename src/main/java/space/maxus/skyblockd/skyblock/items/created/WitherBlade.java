package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.objects.SkyblockAbilityType;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface WitherBlade {
    default SkyblockItemAbility getAbility() {
        List<String> unformatted = Arrays.asList(
                "&7Teleports &a10 blocks&7 ahead of you.",
                "&7Then implode, dealing &ctons&7 of damage",
                "&7to enemies around you.",
                "&7Also gives you &6Instant Healing&7 and",
                "&7short &6Absorption&7 shield."
        );
        List<String> formatted = unformatted.stream().map(str -> ChatColor.translateAlternateColorCodes('&', str)).collect(Collectors.toList());
        return new SkyblockItemAbility(
                "Endless Legacy", SkyblockAbilityType.RIGHT_CLICK, formatted);
    }
}
