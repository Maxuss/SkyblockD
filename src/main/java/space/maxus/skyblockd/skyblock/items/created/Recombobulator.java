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

public class Recombobulator extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD,
                "Recombobulator 4000",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.OTHER_CONSUMABLE,
                new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY+"Right click this item to",
                ChatColor.DARK_GRAY+"upgrade rarity of any item!"
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("recombobulator");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockType"), PersistentDataType.STRING, "RECOMB");
        m.getPersistentDataContainer().remove(SkyblockD.getKey("blockClicks"));
        m.getPersistentDataContainer().remove(SkyblockD.getKey("headClick"));
        i.setItemMeta(m);
        return i;
    }
    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzBhMzU5NTdmY2UyMzQ0YWE4NTU3YmE2YjM2MTg3YWI0MTUyNDk5MzQ2MDdiYmJlZDJhMDUzYzNhODZjYWZjMCJ9fX0=";
    }
}
