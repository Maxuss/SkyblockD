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

public class FruitfulReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.FRUITFUL;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.GOLD+"+3 "+ SkyblockConstants.FORTUNE + " Farming Fortune",
                ChatColor.GRAY+"per rarity tier!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        int amount = modifier * 3;
        if(c.has(SkyblockD.getKey("farmingFortune"), PersistentDataType.INTEGER)) {
            amount += Objects.requireNonNull(c.get(SkyblockD.getKey("farmingFortune"), PersistentDataType.INTEGER));
        }
        c.set(SkyblockD.getKey("farmingFortune"), PersistentDataType.INTEGER, amount);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();

        int amount = modifier * 3;
        Integer pre = c.get(SkyblockD.getKey("farmingFortune"), PersistentDataType.INTEGER);
        assert pre != null;
        c.set(SkyblockD.getKey("farmingFortune"), PersistentDataType.INTEGER, pre-amount);
        item.setItemMeta(m);
    }
}
