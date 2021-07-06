package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import space.maxus.skyblockd.SkyblockD;

import java.util.ArrayList;
import java.util.List;

public class GetEntriesCommand implements ChatCommand {
    @Override
    public String getName() {
        return "entries";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            List<String> msgs = new ArrayList<>();
            msgs.add(ChatColor.GOLD + "All registered namespace entries from SkyblockD:");
            msgs.addAll(SkyblockD.registeredEntries);
            sender.sendMessage((String[]) msgs.toArray());
            return true;
        });
    }
}
