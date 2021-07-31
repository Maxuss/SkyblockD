package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;
import java.util.List;

public class TestReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0.7f;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.TEST;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(ChatColor.GRAY+"Does some stuff", ChatColor.GRAY+"TEST");
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) { }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
    }
}
