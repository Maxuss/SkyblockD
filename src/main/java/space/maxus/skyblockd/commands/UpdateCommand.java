package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;

@CommandInfo(name = "updatesb", permission = "skyblockd.admin", playerOnly = false, configReq = "")
public class UpdateCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        String msg = ChatColor.RED + "[Important] "
                + ChatColor.YELLOW + "This server will reload soon: "
                + ChatColor.AQUA + "For a game update\n"
                + ChatColor.YELLOW + "You have "
                + ChatColor.GREEN + "30 seconds "
                + ChatColor.YELLOW + "to prepare for reload!";
        String title = ChatColor.YELLOW + "" + ChatColor.BOLD + "SERVER RELOAD!";
        String subtitle = ChatColor.YELLOW + "For a game update! " + ChatColor.GRAY + "(in "
                + ChatColor.YELLOW + "0:30" + ChatColor.GRAY + ")";
        Bukkit.broadcastMessage(msg);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 10, 70, 20);
        }
        SkyblockD.getHost().getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(),
                () -> {
                    Bukkit.broadcastMessage(ChatColor.RED + "Reloading...");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
                    Bukkit.broadcastMessage(ChatColor.RED + "Reloaded the server successfully!");
                }, 600);
        return true;
    }
}
