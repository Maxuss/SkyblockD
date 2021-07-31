package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;
import java.util.List;

public class RustyReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.RUSTY;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.GREEN+"Poison"+ChatColor.GRAY+" all enemies you attack",
                ChatColor.GRAY+"Dealing "+ChatColor.RED+"10 dmg/s"+ChatColor.GRAY+" for 5 seconds!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
    }
}
