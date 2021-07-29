package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.gui.ReforgeGui;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        ReforgeGui g = new ReforgeGui();
        p.openInventory(g.generateContains(Bukkit.createInventory(g.getHolder(p), g.getSize(), g.getName())));
        return true;
    }
}
