package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

public class TestReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0.7f;
    }

    @Override
    public SkyblockReforge getReforge() {
        return SkyblockReforge.TEST;
    }

    @Override
    public SkyblockItemStats getBaseDisplayStats() {
        return new SkyblockItemStats().setDamage(100).setDefense(50).setMagicFind(25).setHealth(15);
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) { }
}
