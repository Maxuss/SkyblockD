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

public class KasmirSoulFragment extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.PLAYER_HEAD,
                "Kasmir's Soul Fragment",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.DARK_GRAY+"Dark matter extracted from soul of",
                ChatColor.DARK_GRAY+"Kasmir..."
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("kasmir_soul");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("blockClicks"), PersistentDataType.BYTE, (byte)0);
        m.getPersistentDataContainer().remove(SkyblockD.getKey("headClicks"));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ3NDk5NWQ2Y2RhMmI4YTI0NzcyYWY5NjllZjA3N2FlM2E4NWUyMzU3YzZmNjExOWI4YTI1MDYwNDFhNDQ4YiJ9fX0=";
    }
}