package space.maxus.skyblockd.commands;

import org.bukkit.command.CommandExecutor;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.utils.ManagerBase;

import java.util.Objects;

public class CommandManager extends ManagerBase<ChatCommand> {

    @Override
    public void register() {
        if (!contains.isEmpty()) {
            for (ChatCommand cmd : contains) {
                try {
                    String n = cmd.getName();
                    CommandExecutor e = cmd.getExecutor();
                    Objects.requireNonNull(SkyblockD.getInstance().getCommand(n)).setExecutor(e);
                    SkyblockD.logger.info("Successfully registered command " + n + "!");
                } catch (Exception ex) {
                    SkyblockD.logger.info("Could not register command " + cmd.getName() + "! Exception: " + ex.getMessage());
                }
            }
        }
    }
}
