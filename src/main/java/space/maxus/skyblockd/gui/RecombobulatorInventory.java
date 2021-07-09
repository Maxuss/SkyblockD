package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.Arrays;

public class RecombobulatorInventory extends InventoryBase {
    @Override
    public String getName() {
        return "Recombobulate an item";
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public Inventory generateContains(Inventory base) {
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack rec = new ItemStack(Material.ANVIL);
        ItemMeta m = rec.getItemMeta();
        assert m != null;
        m.setDisplayName(ChatColor.GOLD+"Recombobulate!");
        m.setLore(Arrays.asList(
                "",
                ChatColor.YELLOW+"Right click this item to",
                ChatColor.YELLOW+"recombobulate the item in slot!"));
        m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        rec.setItemMeta(m);

        base.setContents(new ItemStack[] {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, null, gls, gls, gls, gls,
                gls, gls, gls, gls, rec, gls, gls, gls, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls
        });

        return base;
    }

    @Override
    public String getId() {
        return SkyblockD.getNamespace("recombobulator_inventory");
    }

    @Override
    public InventoryHolder getHolder(Player p) {
        return p;
    }
}
