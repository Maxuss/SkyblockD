package space.maxus.skyblockd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpawnCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Arrays.stream(EntitySummon.values()).map(Enum::name).collect(Collectors.toList());
    }
}
