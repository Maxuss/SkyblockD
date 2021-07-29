package space.maxus.skyblockd.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VaultGuiTemplate {
    public Inventory create(int page, Player p) {
        SkyblockMaterial[] mats = SkyblockMaterial.values();

        SkyblockMaterial[][] pages = splitInto(mats, 28);

        SkyblockMaterial[] actualContents;
        if(page > pages.length) actualContents = pages[pages.length-1];
        else if(page < 0) actualContents = pages[0];
        else {
            try {
                actualContents = pages[page];
            } catch (ArrayIndexOutOfBoundsException e) {
                actualContents = pages[pages.length-1];
            }
        }

        Inventory inv = Bukkit.createInventory(p, 54, "The Vault " + page);
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack nxt = GuiHelper.genSimpleMenuItem(ChatColor.GREEN+"Next Page", Material.ARROW, Collections.emptyList());
        ItemStack bck = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Previous Page", Material.ARROW, Collections.emptyList());
        inv.setContents(new ItemStack[]{
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, gls, bck, gls, gls, gls, nxt, gls, gls
        });

        List<ItemStack> stacks =  Arrays.stream(actualContents).map(SkyblockMaterial::getItem).collect(Collectors.toList());
        ItemStack[] contents = stacks.stream().map(i -> i == null ? i = new ItemStack(Material.OAK_LOG) : i).toArray(ItemStack[]::new);
        inv.addItem(contents);

        return inv;
    }

    public static class VaultItemTemplate {
        public Inventory create(ItemStack theItem, Player p) {
            Inventory inv = Bukkit.createInventory(p, 54, "Vault Get " + ChatColor.stripColor(Objects.requireNonNull(theItem.getItemMeta()).getDisplayName()));
            ItemStack gls = GuiHelper.getMenuGlass();
            ItemStack bck = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Previous Page", Material.ARROW, Collections.emptyList());
            ItemStack air = new ItemStack(Material.AIR);

            ItemStack[] contents = {
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, gls, air, air, air, air, air, gls, gls,
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, gls, gls, gls, gls, gls, gls, gls, gls,
                    gls, gls, gls, gls, bck, gls, gls, gls, gls,
            };

            inv.setContents(contents);

            ItemStack i1 = theItem.clone();
            ItemStack i8 = theItem.clone();
            ItemStack i16 = theItem.clone();
            ItemStack i32 = theItem.clone();
            ItemStack i64 = theItem.clone();

            i1.setAmount(1);
            i8.setAmount(8);
            i16.setAmount(16);
            i32.setAmount(32);
            i64.setAmount(64);

            inv.setItem(20, i1);
            inv.setItem(21, i8);
            inv.setItem(22, i16);
            inv.setItem(23, i32);
            inv.setItem(24, i64);

            return inv;
        }
    }

    private SkyblockMaterial[][] splitInto(SkyblockMaterial[] chunk, final int chunkSize) {
        final int length = chunk.length;
        final SkyblockMaterial[][] dest = new SkyblockMaterial[(length + chunkSize - 1)/chunkSize][];
        int destIndex = 0;
        int stopIndex = 0;

        for (int startIndex = 0; startIndex + chunkSize <= length; startIndex += chunkSize)
        {
            stopIndex += chunkSize;
            dest[destIndex++] = Arrays.copyOfRange(chunk, startIndex, stopIndex);
        }

        if (stopIndex < length)
            dest[destIndex] = Arrays.copyOfRange(chunk, stopIndex, length);

        return dest;
    }
}
