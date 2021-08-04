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

public class EmeraldLeggings extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_LEGGINGS, "Emerald Leggings",
                SkyblockRarity.RARE, SkyblockItemType.LEGGINGS,
                new SkyblockItemStats().setDefense(40).setStrength(30).setHealth(30)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Contains 80% Emeralds and 20% Glass"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("emerald_leggings");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 3, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 3, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 4, AttributeModifier.Operation.ADD_NUMBER));
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(66, 245, 56));
        i.setItemMeta(m);
        return i;
    }
}
