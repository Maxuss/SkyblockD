package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;

import java.util.Arrays;

public class ReforgeGui extends InventoryBase {
    @Override
    public @NotNull String getName() {
        return "Reforge Item";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public @NotNull Inventory generateContains(@NotNull Inventory base) {
        ItemStack ref = GuiHelper.genSimpleMenuItem(
                ChatColor.GREEN+"Reforge", Material.ANVIL,
                Arrays.asList(ChatColor.GRAY+"Put item to be reforged on",
                        ChatColor.GRAY+"the left, and reforge stone",
                        ChatColor.GRAY+"on the right, then click this",
                        ChatColor.GRAY+"to reforge!"));
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack air = new ItemStack(Material.AIR);

        ItemStack[] contents = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, ref, gls, gls, gls, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, air, gls, gls, gls, gls, gls, air, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls
        };

        base.setContents(contents);

        return base;
    }

    @Override
    public @NotNull String getId() {
        return SkyblockD.getNamespace("reforge_gui");
    }
}
