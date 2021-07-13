package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.created.Mining;

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
                p.getInventory().addItem(SkyblockMaterial.ICARUS_WINGS.getItem());
                Mining m = new Mining(p);
                p.openInventory(m.generateMenu());
                p.updateInventory();
                return true;
            }
            return false;
        });
    }
}
