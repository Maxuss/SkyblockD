package space.maxus.skyblockd.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.objects.PlayerContainer;

@CommandInfo(name="playtime", playerOnly = true)
public class PlaytimeCommand extends ChatCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        long currentTime = System.currentTimeMillis();
        PlayerContainer pc = ContainerHelper.getPlayer(p);
        long div = currentTime-pc.firstJoin;
        int minute = 60000;
        int hour = minute * 60;
        StringBuilder text = new StringBuilder();
        if (div > hour) {
            text.append(div / hour).append(" hours ");
            div %= hour;
        }
        if(div > minute) {
            text.append(div / minute).append(" minutes");
        }
        String str = text.toString();
        String fullName = ChatColor.GREEN+"Your playtime is "+str;
        p.sendMessage(fullName);
        return false;
    }
}
