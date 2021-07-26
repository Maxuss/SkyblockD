package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.gui.VaultGuiTemplate;

@CommandInfo(name="vault",permission="skyblockd.admin",playerOnly=true)
public class VaultCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        VaultGuiTemplate t = new VaultGuiTemplate();
        p.openInventory(t.create(0, p));
        p.updateInventory();
        return true;
    }
}
