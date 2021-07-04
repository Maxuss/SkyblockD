package xyz.voidmoment.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import xyz.voidmoment.skyblockd.SkyblockD;

public class DevTestCommand implements ICommand{
    @Override
    public String getName() {
        return "sbdevtest";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if(sender instanceof Player)
            {
                Player p = (Player) sender;
                p.openInventory(SkyblockD.getInventories().get("test"));
                p.sendMessage("Opened an inventory!");
                return true;
            }
            return false;
        });
    }
}
