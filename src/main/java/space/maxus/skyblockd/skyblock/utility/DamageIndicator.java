package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.HologramParams;
import space.maxus.skyblockd.util.Hologram;

import java.util.HashMap;

public class DamageIndicator extends Hologram {

    public DamageIndicator(int damage, Location loc) {
        super(new HologramParams("" + damage, loc));
    }

    @Override
    public HologramParams preInit(HologramParams in) {
        char[] arr = in.getDisplayText().toCharArray();
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, ChatColor> m = SkyblockD.getConsts().getDamageMap();
        for (int i = 0; i < arr.length; i++) {
            ChatColor cl = m.get(i);
            char ch = arr[i];
            sb.append(cl).append(ch);
        }
        String _final = sb.toString();
        String tf = ChatColor.WHITE + "✧" + _final + ChatColor.WHITE + "✧";
        return new HologramParams(tf, in.getLocation());
    }

    @Override
    public void postInit(HologramParams in, ArmorStand holo) {
        // schedule task for hologram to disappear in ~2 seconds
        SkyblockD.getHost().getScheduler().scheduleSyncDelayedTask(SkyblockD.getInstance(), holo::remove, 20);
    }
}
