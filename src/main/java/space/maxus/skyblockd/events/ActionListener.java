package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.gui.MainMenuGUI;

import java.util.Objects;

public class ActionListener implements Listener {
    @EventHandler
    public void onAction(PlayerInteractEvent e) {
        Action a = e.getAction();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        if ((a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) && i != null) {
            String name = Objects.requireNonNull(i.getItemMeta()).getDisplayName();
            if (name.equalsIgnoreCase(ChatColor.YELLOW + "Skyblock Menu")) {
                MainMenuGUI gui = new MainMenuGUI();
                Inventory inv = Bukkit.createInventory(p, gui.getSize(), gui.getName());
                gui.generateContains(inv);
                p.openInventory(inv);
                p.updateInventory();
            }
        }
    }
}
