package space.maxus.skyblockd.objects;

import org.bukkit.Location;

public class HologramParams {
    private final String displayText;
    private final Location location;

    public String getDisplayText() {
        return displayText;
    }

    public Location getLocation() {
        return location;
    }

    public HologramParams(String txt, Location loc) {
        displayText = txt;
        location = loc;
    }

}
