package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;
import java.util.List;

public class FerociousReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public SkyblockReforge getReforge() {
        return SkyblockReforge.FEROCIOUS;
    }

    @Override
    public List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.GRAY+"When applied on weapons, gives",
                ChatColor.GRAY+"you chance to perform "+ChatColor.RED+" Double Attack"
        );
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
    }
}
