package space.maxus.skyblockd.skyblock.utility;

import java.util.HashMap;
public abstract class SkyblockRegisterer<T extends SkyblockFeature, C> {
    public HashMap<String, C> contains;

    public abstract void register(T registrant);

    public C get(String id) {
        return contains.getOrDefault(id, null);
    }
    public SkyblockRegisterer(){
        contains = new HashMap<>();
    }

}
