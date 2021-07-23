package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class RodOfSeas extends SkyblockItem {

    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.FISHING_ROD, "Rod of Seas",
                SkyblockRarity.RARE, SkyblockItemType.FISHING_ROD,
                new SkyblockItemStats().setSeaCreatureChance(10).setDamage(5));
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Made of purest lily pads!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("rod_of_seas");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.LURE, 5, true);
        m.addEnchant(Enchantment.DURABILITY, 4, true);
        m.addEnchant(Enchantment.LUCK, 3, true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, 10);
        i.setItemMeta(m);
        return i;
    }
}
