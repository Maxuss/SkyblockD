package space.maxus.skyblockd.skyblock.items.created;

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

public class DragonSingularity extends SkyblockSkull {

    @Override
    public SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(Material.PLAYER_HEAD,
                "Dragon Singularity",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats());
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("dragon_singularity");
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
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJlODcwODlmOTMyOWI1NGM5YTU5NjU2MjUzNTQxMDdjN2Y5NmIzMDU0ZjFkZWY4Y2VlYTJiOTBjZTZmOGQifX19=";
    }
}
