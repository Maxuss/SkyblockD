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

public class WitheredReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.WITHERED;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.RED+"+7 "+ SkyblockConstants.STRENGTH + " Strength",
                ChatColor.GRAY+"per rarity tier!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        int amount = modifier * 7;
        if(c.has(SkyblockD.getKey("strength"), PersistentDataType.INTEGER)) {
            amount += Objects.requireNonNull(c.get(SkyblockD.getKey("strength"), PersistentDataType.INTEGER));
        }
        c.set(SkyblockD.getKey("strength"), PersistentDataType.INTEGER, amount);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();

        int amount = modifier * 5;
        Integer pre = c.get(SkyblockD.getKey("strength"), PersistentDataType.INTEGER);
        assert pre != null;
        c.set(SkyblockD.getKey("strength"), PersistentDataType.INTEGER, pre-amount);
        item.setItemMeta(m);
    }
}
