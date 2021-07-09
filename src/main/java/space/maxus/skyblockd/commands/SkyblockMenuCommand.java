package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import space.maxus.skyblockd.gui.MainMenuGUI;

public class SkyblockMenuCommand implements ChatCommand {

    @Override
    public String getName() {
        return "sbmenu";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is only for players!");
                return true;
            }
            Player p = (Player) sender;
            MainMenuGUI m = new MainMenuGUI();
            Inventory inv = Bukkit.createInventory(p, m.getSize(), m.getName());
            m.generateContains(inv);
            p.openInventory(inv);
            p.updateInventory();
            return true;
        });
    }
}
