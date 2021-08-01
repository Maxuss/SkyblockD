package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.gui.WardrobeGui;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @SuppressWarnings("deprecation")
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        WardrobeGui gui = new WardrobeGui();
        p.openInventory(gui.generateContains(Bukkit.createInventory(gui.getHolder(p), gui.getSize(), gui.getName())));
        return true;
    }
}
