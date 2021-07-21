package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@CommandInfo(name = "spawn", permission = "skyblockd.admin", playerOnly = true, configReq = "")
public class SpawnCommand extends ChatCommand {
    private final List<String> entities = Arrays.stream(EntitySummon.values()).map(Enum::name).collect(Collectors.toList());

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) return false;
        Player p = (Player) sender;
        String id = args[0];
        try {
            EntitySummon summon = EntitySummon.valueOf(id.toUpperCase(Locale.ENGLISH));
            summon.summon(p);
            String msg = ChatColor.YELLOW + "Successfully summoned " + summon.getEntity().getName();
            p.sendMessage(msg);
            return true;
        } catch (IllegalArgumentException e) {
            p.sendMessage(ChatColor.RED+"This entity does not exist!");
            return true;
        }
    }

    @Override
    public List<String> onTab(CommandSender sender, String[] args) {
        return entities;
    }
}
