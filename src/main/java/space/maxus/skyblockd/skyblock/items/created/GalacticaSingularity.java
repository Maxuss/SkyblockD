package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class GalacticaSingularity extends SkyblockSkull {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.PLAYER_HEAD,
                "Galactica Singularity",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats());
        cfg.setDescription(Collections.singletonList(
                ChatColor.DARK_GRAY+"It is pulsating with World's energy"
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("galactica_singularity");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("blockClicks"), PersistentDataType.BYTE, (byte)0);
        m.getPersistentDataContainer().remove(SkyblockD.getKey("headClicks"));
        i.setItemMeta(m);
        return i;
    }

    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQzZDI1MGUyNWJiY2EzYTYyYmU1YjNlZjAyY2ZjYWI2ZGNkYzQyNDg4NGM5YTdkNWNjOTVjOWQwIn19fQ==";
    }
}
