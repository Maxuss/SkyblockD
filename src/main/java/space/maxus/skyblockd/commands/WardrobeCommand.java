package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.gui.WardrobeGui;

@CommandInfo(name = "wardrobe", permission = "skyblockd.default", playerOnly = true)
public class WardrobeCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        WardrobeGui gui = new WardrobeGui();
        p.openInventory(gui.generateContains(Bukkit.createInventory(gui.getHolder(p), gui.getSize(), gui.getName())));
        return true;
    }
}