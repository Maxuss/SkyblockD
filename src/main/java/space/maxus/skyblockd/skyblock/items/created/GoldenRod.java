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

import java.util.Collections;

public class GoldenRod extends SkyblockItem {

    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.FISHING_ROD, "Golden Fishing Rod",
                SkyblockRarity.EPIC, SkyblockItemType.FISHING_ROD,
                new SkyblockItemStats().setSeaCreatureChance(17));
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"\"It's like in that video game, Terraria!\""));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("golden_rod");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.LURE, 4, true);
        m.addEnchant(Enchantment.LUCK, 3, true);
        addSeaCreatureChance(17, m);
        i.setItemMeta(m);
        return i;
    }
}
