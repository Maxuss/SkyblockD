package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class FarmhandGlory extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "Farmhand's Glory", SkyblockRarity.LEGENDARY,
                SkyblockItemType.ACCESSORY, new SkyblockItemStats()
        );
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY + "Amplifies crop drops while",
                ChatColor.DARK_GRAY + "in your inventory and replants",
                ChatColor.DARK_GRAY + "all plants you break with items",
                ChatColor.DARK_GRAY + "from your inventory!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("farmhand_glory");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta meta = i.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(SkyblockD.getKey("blockClicks"), PersistentDataType.BYTE, (byte)1);
        meta.getPersistentDataContainer().remove(SkyblockD.getKey("headClick"));
        i.setItemMeta(meta);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY1ODQ5YWY0ZmMxYTc0ODQ3YmJlMmU3ZmVkZDJlYWIyY2RhMjU0NjUxZTViNmM0YWJhNzk0OTNhODMxN2FkNSJ9fX0=";
    }
}
