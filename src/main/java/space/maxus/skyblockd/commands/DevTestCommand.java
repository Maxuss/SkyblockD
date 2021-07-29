package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import space.maxus.skyblockd.gui.RecipeGuiTemplate;

@CommandInfo(name = "sbdevtest", permission = "skyblockd.admin", playerOnly = true, configReq = "skyblockd.commands.devmode")
public class DevTestCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        RecipeGuiTemplate temp = new RecipeGuiTemplate();
        Inventory inv = temp.create(0, p);
        p.openInventory(inv);
        p.updateInventory();
        return true;
    }
}
