package space.maxus.skyblockd.objects;

import org.bukkit.Location;

public class HologramParams {
    private String displayText;
    private Location location;

    public String getDisplayText() {return displayText;}
    public Location getLocation() {return location;}

    public HologramParams(String txt, Location loc){
        displayText = txt;
        location = loc;
    }

}
