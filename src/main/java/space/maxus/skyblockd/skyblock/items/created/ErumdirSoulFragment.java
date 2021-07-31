package space.maxus.skyblockd.skyblock.items.created;

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

public class ErumdirSoulFragment extends SkyblockSkull {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(Material.PLAYER_HEAD,
                "Erumdir's Soul Fragment",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats());
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("erumdir_soul");
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

    @Override
    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7ImlkIjoiMTcxODY0ODUzNzZkNDNhMjhjNWYzYjFmYzk5Nzk2ZDkiLCJ0eXBlIjoiU0tJTiIsInVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzVkM2E5MGY0NzFjOTVmY2M5NzAyZjZmZTU3M2NjMTEzY2RmNmQ4YzUzOWIyNjFlZTNjMzA3NzFiMThlMmFjIiwicHJvZmlsZUlkIjoiODAxOGFiMDBiMmFlNDRjYWFjOWJmNjBlZjkwZjQ1ZTUiLCJ0ZXh0dXJlSWQiOiI3NWQzYTkwZjQ3MWM5NWZjYzk3MDJmNmZlNTczY2MxMTNjZGY2ZDhjNTM5YjI2MWVlM2MzMDc3MWIxOGUyYWMifX0sInNraW4iOnsiaWQiOiIxNzE4NjQ4NTM3NmQ0M2EyOGM1ZjNiMWZjOTk3OTZkOSIsInR5cGUiOiJTS0lOIiwidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NWQzYTkwZjQ3MWM5NWZjYzk3MDJmNmZlNTczY2MxMTNjZGY2ZDhjNTM5YjI2MWVlM2MzMDc3MWIxOGUyYWMiLCJwcm9maWxlSWQiOiI4MDE4YWIwMGIyYWU0NGNhYWM5YmY2MGVmOTBmNDVlNSIsInRleHR1cmVJZCI6Ijc1ZDNhOTBmNDcxYzk1ZmNjOTcwMmY2ZmU1NzNjYzExM2NkZjZkOGM1MzliMjYxZWUzYzMwNzcxYjE4ZTJhYyJ9LCJjYXBlIjpudWxsfQ==";
    }
}
