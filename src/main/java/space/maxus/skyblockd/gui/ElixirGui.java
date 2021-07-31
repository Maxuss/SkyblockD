package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.Arrays;

public class ElixirGui extends InventoryBase {
    @Override
    public @NotNull String getName() {
        return "Elixir Brewer";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public @NotNull Inventory generateContains(@NotNull Inventory base) {
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack grn = GuiHelper.getMenuGlass();
        grn.setType(Material.LIME_STAINED_GLASS_PANE);
        ItemStack air = new ItemStack(Material.AIR);

        ItemStack brw = GuiHelper.genSimpleMenuItem(ChatColor.YELLOW+"Brew", Material.CAULDRON,
                Arrays.asList(ChatColor.GRAY+"Right click this item to brew",
                        ChatColor.GRAY+"provided ingredient into elixir!"));

        ItemStack[] contains = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, air, gls, gls, gls, gls,
                gls, gls, gls, gls, brw, gls, gls, gls, gls,
                gls, gls, gls, gls, grn, gls, gls, gls, gls,
                gls, gls, gls, gls, air, gls, gls, gls, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
        };

        base.setContents(contains);

        return base;
    }

    @Override
    public @NotNull String getId() {
        return SkyblockD.getNamespace("elixir_brewer");
    }

    public InventoryHolder getHolder(Player p) {return p;}
}
