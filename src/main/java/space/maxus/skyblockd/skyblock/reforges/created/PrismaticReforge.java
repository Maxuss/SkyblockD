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

public class PrismaticReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public @NotNull SkyblockReforge getReforge() {
        return SkyblockReforge.PRISMATIC;
    }

    @Override
    public @NotNull List<String> getDisplayStats() {
        return Arrays.asList(
                ChatColor.LIGHT_PURPLE+"+5 "+ SkyblockConstants.ABILITY_DAMAGE + " Ability Damage",
                ChatColor.GRAY+"per rarity tier!"
        );
    }

    @Override
    public void applyBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        int amount = modifier * 5;
        if(c.has(SkyblockD.getKey("abilDamage"), PersistentDataType.INTEGER)) {
            amount += Objects.requireNonNull(c.get(SkyblockD.getKey("abilDamage"), PersistentDataType.INTEGER));
        }
        c.set(SkyblockD.getKey("abilDamage"), PersistentDataType.INTEGER, amount);
        item.setItemMeta(m);
    }

    @Override
    public void removeBaseStats(int modifier, @NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();

        int amount = modifier * 5;
        Integer pre = c.get(SkyblockD.getKey("abilDamage"), PersistentDataType.INTEGER);
        assert pre != null;
        c.set(SkyblockD.getKey("abilDamage"), PersistentDataType.INTEGER, pre-amount);
        item.setItemMeta(m);
    }
}
