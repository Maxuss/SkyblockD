package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class CoralRod extends SkyblockItem {

    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.FISHING_ROD, "Coral Infused Fishing Rod",
                SkyblockRarity.RARE, SkyblockItemType.FISHING_ROD,
                new SkyblockItemStats().setSeaCreatureChance(13));
        cfg.setDescription(Arrays.asList(
                ChatColor.GRAY + "This rod gives higher Sea Creature",
                ChatColor.GRAY + "Chance in warm ocean!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("coral_rod");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.LURE, 2, true);
        m.addEnchant(Enchantment.LUCK, 3, true);
        addSeaCreatureChance(12, m);
        i.setItemMeta(m);
        return i;
    }
}
