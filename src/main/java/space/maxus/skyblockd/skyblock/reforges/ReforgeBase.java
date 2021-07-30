package space.maxus.skyblockd.skyblock.reforges;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class ReforgeBase implements SkyblockFeature {
    public String getSkyblockId() {
        return SkyblockD.getNamespace(getReforge().getDisplayName()).toLowerCase(Locale.ENGLISH).replace(" ", "_");
    }

    public abstract float getRarityWeight();
    public abstract SkyblockReforge getReforge();
    public abstract List<String> getDisplayStats();
    public abstract void applyBaseStats(int modifier, ItemStack item);
    public abstract void removeBaseStats(int modifier, ItemStack item);

    public void apply(ItemStack item) {
        if(item == null) return;
        if(!item.hasItemMeta()) return;

        ItemMeta m = item.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        if(!c.has(SkyblockD.getKey("skyblockNative"), PersistentDataType.BYTE)) {
            CustomItem.toSkyblockItem(item);
        }

        Integer rar = c.get(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER);
        assert rar != null;
        SkyblockRarity itemRarity = SkyblockRarity.byIndex(rar);

        if(c.has(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER)) {
            String[] newName = m.getDisplayName().split("\\s");
            newName[0] = CustomItem.capitalize(getReforge().getDisplayName());
            String name = itemRarity.displayColor + String.join(" ", newName);

            m.setDisplayName(name);

            List<String> lore = m.getLore();

            Integer data = c.get(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER);
            assert data != null && lore != null;

            SkyblockReforge ref = SkyblockReforge.byIndex(data);

            lore.remove(ChatColor.BLUE + ref.getDisplayName() + " bonuses:");
            lore.removeAll(ref.getBase().getDisplayStats());
            m.setLore(lore);

            ref.getBase().removeBaseStats(Math.round(rar-getRarityWeight()), item);
        } else {
            String newName = itemRarity.displayColor + getReforge().getDisplayName() + " " + m.getDisplayName();
            m.setDisplayName(newName);
        }

        c.set(SkyblockD.getKey("reforged"), PersistentDataType.BYTE, (byte)1);
        c.set(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER, getReforge().getIndex());
        boolean wasReforged = false;
        if(c.has(SkyblockD.getKey("previous"), PersistentDataType.INTEGER_ARRAY)) {
            int[] previous = c.get(SkyblockD.getKey("previous"), PersistentDataType.INTEGER_ARRAY);
            Integer[] nprev = new Integer[previous.length+1];
            for(int i = 0; i < previous.length; i++) {
                nprev[i] = previous[i];
            }
            List<Integer> l = Arrays.asList(nprev);
            wasReforged = l.contains(getReforge().getIndex());
            Integer[] arrayd = l.toArray(new Integer[0]);
            arrayd[arrayd.length-1] = getReforge().getIndex();

            int[] intArray = Arrays.stream(arrayd).mapToInt(i -> i).toArray();

            c.set(SkyblockD.getKey("previous"), PersistentDataType.INTEGER_ARRAY, intArray);
        } else {
            int[] nprev = {getReforge().getIndex()};

            c.set(SkyblockD.getKey("previous"), PersistentDataType.INTEGER_ARRAY, nprev);
        }

        List<String> lore = m.getLore();
        assert lore != null;

        List<String> disp = getDisplayStats();

        lore.remove(ChatColor.DARK_GRAY+"This item can be reforged!");
        reverseList(lore);
        lore.remove(" ");
        if(c.has(SkyblockD.getKey("reforgeData"), PersistentDataType.INTEGER)) lore.remove(" ");
        reverseList(lore);
        lore.add(lore.size()-1, " ");
        lore.add(lore.size()-1, ChatColor.BLUE+getReforge().getDisplayName()+" bonuses:");
        lore.addAll(lore.size()-1, disp);
        lore.add(lore.size()-1, " ");

        m.setLore(lore);

        item.setItemMeta(m);

        if(!wasReforged) applyBaseStats(Math.round(rar-getRarityWeight()), item);

    }

    private static <T> void reverseList(List<T> list)
    {
        if (list.size() <= 1) return;
        T value = list.remove(0);
        reverseList(list);
        list.add(value);
    }
}
