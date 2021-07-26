package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class LilyChestplate extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.LEATHER_CHESTPLATE,
                "Lily Chestplate", SkyblockRarity.RARE, SkyblockItemType.CHESTPLATE,
                new SkyblockItemStats().setHealth(25).setSeaCreatureChance(3));
        cfg.setDescription(Arrays.asList(ChatColor.DARK_GRAY+"Who knew lilies could be so", ChatColor.DARK_GRAY+"useful!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("lily_chestplate");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        LeatherArmorMeta m = (LeatherArmorMeta) i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.THORNS, 3, true);
        m.setColor(Color.fromRGB(13,158,105));
        m.setUnbreakable(true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, 3);
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth",
                        2.5,
                        AttributeModifier.Operation.ADD_NUMBER
                ));
        i.setItemMeta(m);
        return i;
    }
}