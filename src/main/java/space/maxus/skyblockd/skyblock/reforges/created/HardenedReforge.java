package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HardenedReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.HARDENED;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.GOLD+"+5 "+ SkyblockConstants.FORTUNE + " Mining "+ChatColor.GRAY+"and"+ChatColor.GOLD+" Excavating Fortune",
                ChatColor.GRAY+"per rarity tier!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        int amount = modifier * 5;
        if(c.has(SkyblockD.getKey("miningFortune"), PersistentDataType.INTEGER)) {
            amount += Objects.requireNonNull(c.get(SkyblockD.getKey("miningFortune"), PersistentDataType.INTEGER));
        }
        c.set(SkyblockD.getKey("miningFortune"), PersistentDataType.INTEGER, amount);
        int amount2 = modifier * 5;
        if(c.has(SkyblockD.getKey("excavatingFortune"), PersistentDataType.INTEGER)) {
            amount2 += Objects.requireNonNull(c.get(SkyblockD.getKey("excavatingFortune"), PersistentDataType.INTEGER));
        }
        c.set(SkyblockD.getKey("excavatingFortune"), PersistentDataType.INTEGER, amount2);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();

        int amount = modifier * 5;
        Integer pre = c.get(SkyblockD.getKey("miningFortune"), PersistentDataType.INTEGER);
        Integer pre2 = c.get(SkyblockD.getKey("excavatingFortune"), PersistentDataType.INTEGER);
        assert pre != null && pre2 != null;
        c.set(SkyblockD.getKey("miningFortune"), PersistentDataType.INTEGER, pre-amount);
        c.set(SkyblockD.getKey("excavatingFortune"), PersistentDataType.INTEGER, pre2-amount);
        item.setItemMeta(m);
    }
}
