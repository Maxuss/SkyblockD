package space.maxus.skyblockd.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.awt.*;
import java.time.Instant;

@CommandInfo(name = "maintenance", permission = "skyblockd.admin", playerOnly = false)
public class MaintenanceCommand extends ChatCommand {
    @Override
    public boolean execute(@NotNull CommandSender sender, String[] args) {
        if(SkyblockD.inMaintenace) {
            SkyblockD.inMaintenace = false;
            sender.sendMessage(ChatColor.GREEN+"Server is now back in normal mode!");
            if(SkyblockD.getDiscord() != null) {
                TextChannel channel = SkyblockD.getDiscord().getChannel();
                EmbedBuilder eb = new EmbedBuilder()
                        .setAuthor("Server State", null, "https://cdn.maxus.space/files/plugins/online.png")
                        .setDescription("Server is back in normal mode!\nYou can join it now!")
                        .setColor(Color.GREEN)
                        .setTimestamp(Instant.now());

                channel.sendMessage(eb.build()).queue();
            }
            return true;
        }
        String reason = "unspecified\n";
        if(args.length > 0) {
            reason = ChatColor.RED+String.join(" ", args)+"\n";
        }
        String msg = ChatColor.RED + "[Important] "
                + ChatColor.YELLOW + "This server is going into maintenance mode!\n"
                + ChatColor.GRAY + "For reason: " + reason
                + ChatColor.YELLOW + "You have "
                + ChatColor.GREEN + "5 seconds "
                + ChatColor.YELLOW + "to prepare for maintenance!";
        String title = ChatColor.RED + "" + ChatColor.BOLD + "SERVER MAINTENANCE!";
        String subtitle = ChatColor.GRAY + "(in " + ChatColor.YELLOW + "0:05" + ChatColor.GRAY + ")";
        Bukkit.broadcastMessage(msg);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(title, subtitle, 10, 70, 20);
        }
        String finalReason = reason;
        SkyblockD.getHost().getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(),
                () -> {
                    Bukkit.broadcastMessage(ChatColor.RED + "Changing server state...");
                    SkyblockD.inMaintenace = true;
                    kickEveryone();
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is now in maintenance");

                    if(SkyblockD.getDiscord() != null) {
                        TextChannel channel = SkyblockD.getDiscord().getChannel();
                        EmbedBuilder eb = new EmbedBuilder()
                                .setAuthor("Server State", null, "https://cdn.maxus.space/files/plugins/offline.png")
                                .setDescription("Server has gone into maintenance!\nThe server will remain closed\nfor unknown period of time!\n\nMaintenance reason: "+ChatColor.stripColor(finalReason))
                                .setColor(Color.RED)
                                .setTimestamp(Instant.now());

                        channel.sendMessage(eb.build()).queue();
                    }
                }, 100);
        return true;
    }

    private void kickEveryone() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.hasPermission("skyblockd.admin")) p.kickPlayer(ChatColor.RED+"The server was put into maintenance mode!\n"+ChatColor.GRAY+"Please wait for official updates!");
        }
    }
}
