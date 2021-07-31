package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

@CommandInfo(name = "maintenance", permission = "skyblockd.admin", playerOnly = false)
public class MaintenanceCommand extends ChatCommand {
    @Override
    public boolean execute(@NotNull CommandSender sender, String[] args) {
        if(SkyblockD.inMaintenace) {
            SkyblockD.inMaintenace = false;
            sender.sendMessage(ChatColor.GREEN+"Server is now back in normal mode!");
            return true;
        }
        String msg = ChatColor.RED + "[Important] "
                + ChatColor.YELLOW + "This server is going into maintenance mode!\n"
                + ChatColor.YELLOW + "You have "
                + ChatColor.GREEN + "5 seconds "
                + ChatColor.YELLOW + "to prepare for maintenance!";
        String title = ChatColor.RED + "" + ChatColor.BOLD + "SERVER MAINTENANCE!";
        String subtitle = ChatColor.GRAY + "(in " + ChatColor.YELLOW + "0:05" + ChatColor.GRAY + ")";
        Bukkit.broadcastMessage(msg);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 10, 70, 20);
        }
        SkyblockD.getHost().getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(),
                () -> {
                    Bukkit.broadcastMessage(ChatColor.RED + "Changing server state...");
                    SkyblockD.inMaintenace = true;
                    kickEveryone();
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is now in maintenance");
                }, 100);
        return true;
    }

    private void kickEveryone() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.hasPermission("skyblockd.admin")) p.kickPlayer(ChatColor.RED+"The server was put into maintenance mode!\n"+ChatColor.GRAY+"Please wait for official updates!");
        }
    }
}
