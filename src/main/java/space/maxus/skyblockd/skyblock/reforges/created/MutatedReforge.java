package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;
import java.util.List;

public class MutatedReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.MUTATED;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.GRAY+"Take less damage from undead",
                ChatColor.GRAY+"mobs if applied on armor.",
                ChatColor.GRAY+"Deal more damage to them if",
                ChatColor.GRAY+"applied on weapon!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
    }
}
