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

public class FarmhandSoul extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "Farmhand's Soul", SkyblockRarity.RARE,
                SkyblockItemType.ACCESSORY, new SkyblockItemStats()
        );
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY + "Replants all crops you break",
                ChatColor.DARK_GRAY + "as long as you have enough",
                ChatColor.DARK_GRAY + "items in your inventory"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("farmhand_soul");
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
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVmNzViYWUxMzQ5NmI5N2Y1NWRjNDJmYzM3MjQyYTg4MTU4OTUyYjZjY2U5M2MwNTdhYjAyOGFjYmE4MGIyMCJ9fX0=";
    }
}
