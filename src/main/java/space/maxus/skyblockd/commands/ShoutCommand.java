package space.maxus.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.util.HashMap;
@CommandInfo(name = "shout", playerOnly = true, configReq = "skyblockd.chat.shout.enabled")
public class ShoutCommand extends ChatCommand {

    private final static HashMap<String, Long> lastCommands = new HashMap<>();
    private static final long cd = SkyblockD.getCfg().shoutHasCooldown()
            ? SkyblockD.getCfg().shoutCooldown() * 1000L
            : 0;

    private static @NotNull String formatTime(long time) {
        String r = "";
        if (time >= 60000) {
            r += Math.floor(time / 60000f) + " minutes and ";
        }
        r += Math.ceil(time % 60000f / 1000f) + " seconds";
        return r;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, String @NotNull [] args) {
        if (args.length < 1) return false;
        Long lastCommand = lastCommands.get(sender.getName());
        if (lastCommand == null || lastCommand + cd < System.currentTimeMillis()) {
            Player p = (Player) sender;
            String msg = ChatColor.YELLOW + "[SHOUT] > " + ChatColor.RESET + p.getDisplayName().replaceFirst("B", "") + ChatColor.WHITE + ": " + args[0];
            Bukkit.broadcastMessage(msg);
            lastCommands.put(sender.getName(), System.currentTimeMillis());
        } else {
            sender.sendMessage(ChatColor.YELLOW + "You have to wait " + formatTime(cd - (System.currentTimeMillis() - lastCommand)) + " before you can use this command again!");
        }
        return true;
    }
}
