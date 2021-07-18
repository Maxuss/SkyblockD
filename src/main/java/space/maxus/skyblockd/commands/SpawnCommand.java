package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.skyblock.entities.EntitySummon;

import java.util.Locale;

public class SpawnCommand implements ChatCommand {
    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is only for players!");
                return true;
            }
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
        });
    }
}
