package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.HologramParams;
import space.maxus.skyblockd.skyblock.DamageIndicator;
import space.maxus.skyblockd.skyblock.SimpleHologram;
import space.maxus.skyblockd.utils.Hologram;

public class DevTestCommand implements ChatCommand {
    @Override
    public String getName() {
        return "sbdevtest";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if (sender instanceof Player) {
                if(args.length < 1) return false;
                Player p = (Player) sender;
                Location loc = p.getLocation();
                loc.setY(loc.getY()+1);
                if(args[0].equalsIgnoreCase("dmg")) {
                    DamageIndicator indicator = new DamageIndicator(1234567890, loc);
                    p.sendMessage("Created damage indicator!");
                }
                else if(args[0].equalsIgnoreCase("holo"))
                {
                    SimpleHologram holo = new SimpleHologram(ChatColor.YELLOW+"Hello world!", loc);
                    p.sendMessage("Created hologram!!");
                }
                return true;
            }
            return false;
        });
    }
}
