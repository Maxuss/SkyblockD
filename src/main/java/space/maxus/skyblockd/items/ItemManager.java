package space.maxus.skyblockd.items;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.utils.ManagerBase;

import java.util.TreeMap;

public class ItemManager extends ManagerBase<CustomItem> {

    public TreeMap<String, ItemStack> generated;

    public ItemManager() {
        generated = new TreeMap<>();
    }

    @Override
    public void register() {
        if (!contains.isEmpty()) {
            for (CustomItem i : contains) {
                ItemStack item = new ItemStack(i.getMaterial(), i.getCount());
                item.setItemMeta(i.generateMeta(item.getItemMeta()));
                generated.put(i.getId(), item);
            }
        }
    }
}
