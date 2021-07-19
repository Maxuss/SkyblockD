package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.nms.NMSColor;
import space.maxus.skyblockd.nms.PacketUtils;

public class DevTestCommand implements ChatCommand {
    @Override
    public String getName() {
        return "sbdevtest";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                PacketUtils.sendActionbar(p, "Hello, this is an action bar!", NMSColor.GREEN);
                p.updateInventory();
                return true;
            }
            return false;
        });
    }
}
