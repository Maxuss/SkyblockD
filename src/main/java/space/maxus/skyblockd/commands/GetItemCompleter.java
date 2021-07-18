package space.maxus.skyblockd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetItemCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return Arrays.stream(SkyblockMaterial.values()).map(Enum::name).collect(Collectors.toList());
    }
}
