package space.maxus.skyblockd.helpers;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;

import java.util.List;

public class GuiHelper {
    public static ItemStack getMenuGlass() {
        ItemStack s = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = s.getItemMeta();
        assert m != null;
        setHideAllFlags(m);
        m.setDisplayName(" ");
        m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        s.setItemMeta(m);
        return s;
    }

    public static ItemMeta setHideAllFlags(ItemMeta m) {
        m.addItemFlags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE);
        return m;
    }

    public static ItemStack getPlayerHead(Player player) {
        return SkullCreator.itemFromUuid(player.getUniqueId());
    }

    public static ItemStack genSimpleMenuItem(String name, Material material, List<String> lore) {
        ItemStack i = new ItemStack(material);
        ItemMeta sm = i.getItemMeta();
        assert sm != null;
        sm.setDisplayName(ChatColor.YELLOW + name);
        sm.setLore(lore);
        sm.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        i.setItemMeta(GuiHelper.setHideAllFlags(sm));
        return i;
    }
}
