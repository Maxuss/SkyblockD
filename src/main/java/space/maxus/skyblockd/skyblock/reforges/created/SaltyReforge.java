package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.List;

public class SaltyReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public SkyblockReforge getReforge() {
        return SkyblockReforge.SALTY;
    }

    @Override
    public List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.AQUA + "+1 " + SkyblockConstants.SCC + " Sea Creature Chance",
                ChatColor.GRAY+ "per rarity of item");
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, modifier);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().remove(SkyblockD.getKey("scc"));
        item.setItemMeta(m);
    }
}
