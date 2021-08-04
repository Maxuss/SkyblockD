package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class GemstoneChestplate extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_CHESTPLATE, "Gemstone Chestplate",
                SkyblockRarity.RARE, SkyblockItemType.CHESTPLATE,
                new SkyblockItemStats().setDefense(60).setStrength(50).setHealth(40)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Very heavy"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("gemstone_chestplate");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 5, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 4, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER));
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(172, 21, 237));
        i.setItemMeta(m);
        return i;
    }
}
