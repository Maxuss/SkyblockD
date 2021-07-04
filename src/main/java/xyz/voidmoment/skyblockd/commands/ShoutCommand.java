package xyz.voidmoment.skyblockd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ShoutCommand implements ChatCommand {

    private final static HashMap<String, Long> lastCommands = new HashMap<>();
    private static final long cd = 25000;


    @Override
    public String getName() {
        return "shout";
    }

    @Override
    public CommandExecutor getExecutor() {
        return ((sender, command, label, args) -> {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED+"This command is only for players!");
                return true;
            }
            if(args.length < 1) return false;
            Long lastCommand = lastCommands.get(sender.getName());
            if (lastCommand == null || lastCommand + cd < System.currentTimeMillis()) {
                Player p = (Player) sender;
                String msg = ChatColor.YELLOW + "[SHOUT] > " + p.getDisplayName().replaceFirst("B", "") + ChatColor.WHITE + ": " +args[0];
                Bukkit.broadcastMessage(msg);
                lastCommands.put(sender.getName(), System.currentTimeMillis());
            } else {
                sender.sendMessage(ChatColor.YELLOW+"You have to wait "+formatTime(cd - (System.currentTimeMillis() - lastCommand))+" before you can use this command again!");
            }
            return true;
        });
    }

    private static String formatTime(long time) {
        String r = "";
        if (time >= 60000) {
            r += Math.floor(time / 60000) + " minutes and ";
        }
        r += Math.ceil(time % 60000 / 1000)+" seconds";
        return r;
    }
}
