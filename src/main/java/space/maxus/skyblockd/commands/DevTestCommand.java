package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.entities.TestEntity;

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
                TestEntity te = new TestEntity(p);
                return true;
            }
            return false;
        });
    }
}
