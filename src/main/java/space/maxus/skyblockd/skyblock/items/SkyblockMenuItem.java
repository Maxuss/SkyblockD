package space.maxus.skyblockd.skyblock.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.items.CustomItem;

import java.util.Arrays;
import java.util.List;

public class SkyblockMenuItem implements CustomItem {

    @Override
    public Material getMaterial() {
        return Material.NETHER_STAR;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public ItemMeta generateMeta(ItemMeta m) {
        m.setDisplayName(ChatColor.YELLOW+"Skyblock Menu");
        List<String> lore = Arrays.asList(
                ChatColor.GRAY+"Right click this item to open", ChatColor.GRAY+"Skyblock menu!"
        );
        m.setLore(lore);
        GuiHelper.setHideAllFlags(m);
        m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        return m;
    }

    @Override
    public String getRawName() {
        return SkyblockD.getNamespace("skyblock_menu");
    }
}
