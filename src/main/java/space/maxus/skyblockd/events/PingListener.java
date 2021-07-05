package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import space.maxus.skyblockd.SkyblockD;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PingListener implements Listener {

    private int index = 0;

    @EventHandler
    public void onServerPing(ServerListPingEvent e){
        String l1 = ChatColor.YELLOW + "       -=-=- " + SkyblockD.getServerName() + " -=-=-         \n"
                + ChatColor.GOLD + "We have " + ChatColor.AQUA + e.getNumPlayers() + " players online!";
        String l2 = ChatColor.RED + "This server runs " + ChatColor.GOLD +""+ ChatColor.BOLD + SkyblockD.getFullShortName() +ChatColor.RESET+ "" +ChatColor.RED + ",\n"
                + ChatColor.YELLOW + "and more amazing plugins! Visit us!";
        List<String> cfgMsgs = SkyblockD.getCfg().customMotdElements();
        List<String> msgs = Arrays.asList(l1, l2);
        msgs.addAll(cfgMsgs);
        e.setMotd(msgs.get(index));
        loopIndex(msgs.toArray().length);
    }

    public void loopIndex(int len){
        index++;
        if(index >= len) index = 0;
    }
}
