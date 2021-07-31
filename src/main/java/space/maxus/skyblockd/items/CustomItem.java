package space.maxus.skyblockd.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class CustomItem {
    public abstract Material getMaterial();

    public abstract int getCount();

    public abstract ItemMeta generateMeta(ItemMeta empty);

    public abstract String getId();

    public static void toSkyblockItem(@NotNull Item it) {
        ItemStack i = it.getItemStack();
        toSkyblockItem(i);
    }

    public static void toSkyblockItem(@NotNull ItemStack i){
        ItemHelper.getExtraStats(i);
        ItemMeta m = i.getItemMeta();
        assert m != null;
        GuiHelper.setHideAllFlags(m);
        List<String> l;
        SkyblockRarity r = ItemHelper.getRarity(i.getType());
        if(m.hasLore()){
            l = m.getLore();
        } else l = new ArrayList<>();
        assert l != null;
        l.add(r.displayName+" "+ItemHelper.getType(i.getType()).getDisplay());
        m.setLore(l);
        String tn = i.getType().toString().replace("_", " ").toLowerCase(Locale.ENGLISH);
        String name = r.displayColor + (m.hasDisplayName() ? m.getDisplayName() : capitalize(tn));
        m.setDisplayName(name);

        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
        m.getPersistentDataContainer().set(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER, r.getIndex());
        i.setItemMeta(m);
    }

    public static @NotNull String capitalize(@NotNull String str){
        String[] words = str.split("\\s");
        StringBuilder cap = new StringBuilder();
        for(String w: words){
            String first = w.substring(0,1);
            String afterfirst = w.substring(1);
            cap.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return cap.toString().trim();
    }
}
