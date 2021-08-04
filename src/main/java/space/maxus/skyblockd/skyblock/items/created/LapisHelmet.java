package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Collections;

public class LapisHelmet extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.SEA_LANTERN, "Lapis Helmet",
                SkyblockRarity.UNCOMMON, SkyblockItemType.BOOTS,
                new SkyblockItemStats().setHealth(30)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.GOLD+"+10 "+ SkyblockConstants.FORTUNE+" Mining Fortune"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("lapis_helmet");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 3, AttributeModifier.Operation.ADD_NUMBER));
        addMiningFortune(10, m);
        m.getPersistentDataContainer().set(SkyblockD.getKey("headClick"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
