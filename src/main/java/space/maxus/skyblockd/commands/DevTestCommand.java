package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

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
                p.getWorld().spawnEntity(p.getLocation(), EntityType.PLAYER);
                p.updateInventory();
                return true;
            }
            return false;
        });
    }
}
