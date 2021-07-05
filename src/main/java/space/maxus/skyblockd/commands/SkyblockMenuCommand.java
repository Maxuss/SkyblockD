package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;

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
            p.openInventory(SkyblockD.getInventories().get("sbd.menus.main"));
            return true;
        });
    }
}
