package xyz.voidmoment.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import xyz.voidmoment.skyblockd.SkyblockD;

public class SkyblockMenuCommand implements ChatCommand, Listener {

    @Override
    public String getName() {
        return "sbmenu";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED+"This command is only for players!");
                return true;
            }
            Player p = (Player) sender;
            p.openInventory(SkyblockD.getInventories().get("sbd.menus.main"));
            return true;
        });
    }

    @EventHandler
    public void onInteract(InventoryInteractEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().contains("SkyblockD Menu")){
            e.setResult(Event.Result.DENY);
            p.updateInventory();
        }
    }
}
