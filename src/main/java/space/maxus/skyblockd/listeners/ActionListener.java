package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.gui.MainMenuGUI;
import space.maxus.skyblockd.gui.RecombobulatorInventory;
import space.maxus.skyblockd.objects.BetterListener;

import java.util.Objects;

public class ActionListener extends BetterListener {

    @EventHandler
    public void onAction(@NotNull PlayerInteractEvent e) {
        Action a = e.getAction();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        if(i == null) return;
        if(Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("blockClicks"), PersistentDataType.BYTE)) {
            e.setCancelled(true);
            return;
        }
        if(i.getItemMeta().getPersistentDataContainer().has(SkyblockD.getKey("headClick"), PersistentDataType.BYTE)) {
            e.setCancelled(true);
            PlayerInventory inv = p.getInventory();
            inv.remove(i);
            if(inv.getHelmet() != null) {
                inv.addItem(inv.getHelmet());
            }
            inv.setHelmet(i);
        }
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            String name = Objects.requireNonNull(i.getItemMeta()).getDisplayName();
            if (name.equalsIgnoreCase(ChatColor.YELLOW + "Skyblock Menu")) {
                MainMenuGUI gui = new MainMenuGUI();
                gui.setPlayer(p);
                Inventory inv = Bukkit.createInventory(p, gui.getSize(), gui.getName());
                gui.generateContains(inv);
                p.openInventory(inv);
                p.updateInventory();
            } else if(name.equalsIgnoreCase(ChatColor.GOLD+"Recombobulator 4000")){
                RecombobulatorInventory in = new RecombobulatorInventory();
                Inventory tr = Bukkit.createInventory(in.getHolder(p), in.getSize(), in.getName());
                in.generateContains(tr);
                p.openInventory(tr);
                p.getInventory().remove(i);
                p.updateInventory();
            }
        }
    }
}
