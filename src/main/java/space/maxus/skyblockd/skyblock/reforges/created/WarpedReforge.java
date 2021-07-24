package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WarpedReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0;
    }

    @Override
    public SkyblockReforge getReforge() {
        return SkyblockReforge.WARPED;
    }

    @Override
    public List<String> getDisplayStats() {
        return Arrays.asList(ChatColor.GRAY+"Heavily increases your damage", ChatColor.GRAY+"while in "+ChatColor.LIGHT_PURPLE+"The End",ChatColor.GREEN+"+ 10 " + SkyblockConstants.DEFENCE+ " Defense");
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 1d, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        double prev = 0;
        if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null && Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR)).size() >= 1) {
            double amount = new ArrayList<>(Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR))).get(0).getAmount();
            prev = amount >= 1d ? amount - 1d : 0;
        }
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        if(prev > 0)
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", prev, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
    }
}
