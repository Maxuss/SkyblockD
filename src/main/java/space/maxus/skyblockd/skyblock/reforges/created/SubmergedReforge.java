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

public class SubmergedReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.SUBMERGED;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.AQUA + "+2 " + SkyblockConstants.SCC + " Sea Creature Chance",
                ChatColor.GRAY+ "per rarity of item");
    }

    @Override
    public void applyBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, modifier*2);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();

        int amount = modifier * 2;
        Integer pre = c.get(SkyblockD.getKey("scc"), PersistentDataType.INTEGER);
        assert pre != null;
        c.set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, pre-amount);
        item.setItemMeta(m);
    }
}
