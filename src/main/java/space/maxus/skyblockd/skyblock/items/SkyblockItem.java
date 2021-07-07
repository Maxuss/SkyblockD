package space.maxus.skyblockd.skyblock.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.ArrayList;
import java.util.List;

public abstract class SkyblockItem extends CustomItem {
    @Override
    public Material getMaterial() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ItemMeta generateMeta(ItemMeta empty) {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    private final ItemStack item;

    public ItemStack getItem() { return item; }

    public SkyblockItem(SkyblockItemConfig cfg){
        List<SkyblockItemAbility> abils = cfg.getAbilities();
        List<String> desc = cfg.getDescription();
        String name = cfg.getName();
        Material mat = cfg.getMaterial();
        SkyblockRarity rar = cfg.getRarity();

        item = new ItemStack(mat, 1);
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.setDisplayName(name);
        List<String> lore = new ArrayList<>();

        if(cfg.getType().isConsumable()) lore.add(ChatColor.DARK_GRAY+"Consumed on use");



        if(!(cfg.getAbilities() == null)){
            // todo finish
        }
    }
}
