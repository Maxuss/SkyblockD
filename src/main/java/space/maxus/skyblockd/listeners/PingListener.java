package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;

import java.util.Arrays;

public class PingListener extends BetterListener {
    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if(SkyblockD.inMaintenace) {
            e.setMotd(SkyblockD.getServerData().meta.getOfflineMotd());
            e.setMaxPlayers(0);
            try {
                e.setServerIcon(Bukkit.loadServerIcon(SkyblockD.getServerData().offlineIcon));
            } catch (Exception ex) {
                SkyblockD.logger.severe("Could not load server icon!"+ Arrays.toString(ex.getStackTrace()) + "\n"+ex.getMessage());
            }
        } else {
            e.setMotd(SkyblockD.getMotd());
            try {
                e.setServerIcon(Bukkit.loadServerIcon(SkyblockD.getServerData().onlineIcon));
            } catch (Exception ex) {
                SkyblockD.logger.severe("Could not load server icon!" + Arrays.toString(ex.getStackTrace()) + "\n" + ex.getMessage());
            }
        }
    }
}
