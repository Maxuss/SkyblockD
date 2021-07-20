package space.maxus.skyblockd.skyblock.items;

import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.ArrayList;
import java.util.List;

public class SimpleItem extends SkyblockItem {

    @Nullable
    @Override
    public SkyblockItemConfig getConfig() {
        return null;
    }

    @Nullable
    @Override
    public boolean hasGlint() {
        return false;
    }

    @Nullable
    @Override
    public String getSkyblockId() {
        return null;
    }

    @Nullable
    @Override
    public ItemStack postInit(ItemStack i) {
        return null;
    }

    @Nullable
    @Override
    public ItemStack generate() {
        return null;
    }

    public SimpleItem(
            String name,
            List<String> description,
            SkyblockRarity rarity,
            Material item,
            boolean hasGlint
            ) {
        ItemStack i = new ItemStack(item);
        ItemMeta meta = i.getItemMeta();
        assert meta != null;
        meta.setDisplayName(rarity.displayColor+name);
        description.add(" ");
        description.add(rarity.displayName);
        addSkyblockTag(meta);
        meta.getPersistentDataContainer().set(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER, rarity.getIndex());
        gen(i, description, hasGlint, meta);
    }

    public SimpleItem(
            String name,
            SkyblockRarity rarity,
            Material item,
            boolean hasGlint
    ) {
        ItemStack i = new ItemStack(item);
        ItemMeta meta = i.getItemMeta();
        assert meta != null;
        meta.setDisplayName(rarity.displayColor+name);
        List<String> description = new ArrayList<>();
        description.add(rarity.displayName);
        addSkyblockTag(meta);
        meta.getPersistentDataContainer().set(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER, rarity.getIndex());
        gen(i, description, hasGlint, meta);
    }

    private void gen(ItemStack base, List<String> lore, boolean hasGlint, ItemMeta m) {
        m.setLore(lore);
        if(hasGlint) ItemHelper.applyGlint(m);
        base.setItemMeta(m);
        item = base;
    }

    protected ItemStack item;

    public ItemStack get() { return item; }
}
