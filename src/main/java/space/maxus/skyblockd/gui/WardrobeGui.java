package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.nms.BaseByteUtils;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.WardrobeSlot;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class WardrobeGui extends InventoryBase {
    @Override
    public @Nullable InventoryHolder getHolder(Player p) {
        return p;
    }

    @Override
    public String getName() {
        return "Wardrobe";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @SuppressWarnings("all")
    @Override
    public Inventory generateContains(Inventory base) {
        ItemStack gls = GuiHelper.getMenuGlass();
        ItemStack sel = GuiHelper.genSimpleMenuItem(
                ChatColor.GREEN+"Select this set", Material.GREEN_TERRACOTTA, Collections.emptyList());
        ItemStack put = GuiHelper.genSimpleMenuItem(
                " ", Material.LIME_STAINED_GLASS_PANE, Collections.emptyList());

        Player p = (Player) base.getHolder();
        assert p != null;
        PlayerContainer pc = ContainerHelper.getPlayer(p);
        List<WardrobeSlot> wardrobe = pc.wardrobe;

        ItemStack[] helmets = new ItemStack[7];
        ItemStack[] chestplates = new ItemStack[7];
        ItemStack[] leggings = new ItemStack[7];
        ItemStack[] boots = new ItemStack[7];

        for(int i = 0; i < wardrobe.size() && i < 7; i++) {
            WardrobeSlot slot = wardrobe.get(i);
            String data = slot.getContents();
            ItemStack[] storaged = new ItemStack[4];
            try {
                storaged = data == null || data.equals("") || BaseByteUtils.itemStackArrayFromBase64(data) == null
                        ? new ItemStack[] { put.clone(), put.clone(), put.clone(), put.clone() }
                        : BaseByteUtils.itemStackArrayFromBase64(data);
            } catch (IOException e) {
                p.sendMessage(ChatColor.RED + "Could not load wardrobe!");
            }
            helmets[i] = storaged[0] == null || storaged[0].getType().isEmpty() ? put.clone() : storaged[0];
            chestplates[i] = storaged[1] == null || storaged[1].getType().isEmpty() ? put.clone() : storaged[1];
            leggings[i] = storaged[2] == null || storaged[2].getType().isEmpty() ? put.clone() : storaged[2];
            boots[i] = storaged[3] == null || storaged[3].getType().isEmpty() ? put.clone() : storaged[3];
        }

        ItemStack[] contents = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, helmets[0], helmets[1], helmets[2], helmets[3], helmets[4], helmets[5], helmets[6], gls,
                gls, chestplates[0], chestplates[1], chestplates[2], chestplates[3], chestplates[4], chestplates[5], chestplates[6], gls,
                gls, leggings[0], leggings[1], leggings[2], leggings[3], leggings[4], leggings[5], leggings[6], gls,
                gls, boots[0], boots[1], boots[2], boots[3], boots[4], boots[5], boots[6], gls,
                gls, sel, sel, sel, sel, sel, sel, sel, gls
        };

        base.setContents(contents);

        return base;
    }

    @Override
    public String getId() {
        return SkyblockD.getNamespace("wardrobe");
    }
}
