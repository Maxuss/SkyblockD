package space.maxus.skyblockd.skyblock.reforges.created;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.skyblock.reforges.ReforgeBase;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MagmaticReforge extends ReforgeBase {
    @Override
    public float getRarityWeight() {
        return 0.0f;
    }

    @Override
    public SkyblockReforge getReforge() {
        return SkyblockReforge.MAGMATIC;
    }

    @Override
    public List<String> getDisplayStats() {
        return Arrays.asList(ChatColor.GRAY+"Heavily increases your "+ ChatColor.RED+"Damage",
                ChatColor.GRAY+"while in " + ChatColor.GOLD+ "The Nether",ChatColor.GREEN+"+10 "+ SkyblockConstants.DEFENCE + " Defense");
    }

    @Override
    public void applyBaseStats(int modifier, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        double prev = 0.0d;
        if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null && Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR)).size() > 0) {
            for(AttributeModifier mod : Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR))) {
                prev += mod.getAmount();
            }
        }
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 1d+prev, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
    }

    @Override
    public void removeBaseStats(int modifier, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        double prev = 0;
        if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null && Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR)).size() >= 1) {
            for(AttributeModifier mod : Objects.requireNonNull(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR))) {
                double amount = mod.getAmount();
                prev += amount;
            }
        }
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", prev-1d, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
    }
}
