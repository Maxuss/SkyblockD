package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import space.maxus.skyblockd.gui.MainMenuGUI;

@CommandInfo(name = "sbmenu", permission = "", playerOnly = true, configReq = "")
public class SkyblockMenuCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        MainMenuGUI m = new MainMenuGUI();
        m.setPlayer(p);
        Inventory inv = Bukkit.createInventory(p, m.getSize(), m.getName());
        m.generateContains(inv);
        p.openInventory(inv);
        p.updateInventory();
        return true;
    }
}
