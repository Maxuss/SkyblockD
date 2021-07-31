package space.maxus.skyblockd.skyblock.items;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;

public abstract class SkyblockSkull extends SkyblockItem {

    public abstract @NotNull SkyblockItemConfig getConfig();
    public abstract boolean hasGlint();
    public abstract @NotNull String getSkyblockId();
    public abstract @Nullable ItemStack postInit(ItemStack i);
    public abstract @NotNull String getSkinHash();

    // works kinda the same
    @Override
    public @NotNull ItemStack generate(){
        ItemStack sk = SkullCreator.itemFromBase64(getSkinHash());
        ItemMeta meta = sk.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(SkyblockD.getKey("headClick"), PersistentDataType.BYTE, (byte)0);
        sk.setItemMeta(meta);
        return inheritanceGeneration(sk);
    }
}
