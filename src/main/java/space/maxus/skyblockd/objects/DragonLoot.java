package space.maxus.skyblockd.objects;

import java.util.HashMap;
import java.util.List;

public class DragonLoot {
    private HashMap<String, Float> vanilla;
    private HashMap<String, Float> skyblock;
    private List<String> guaranteed;

    public HashMap<String, Float> getSkyblock() {
        return skyblock;
    }

    public HashMap<String, Float> getVanilla() {
        return vanilla;
    }

    public List<String> getGuaranteed() {
        return guaranteed;
    }
}
