package space.maxus.skyblockd.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerContainer {
    public RankContainer ranks;
    public UUID uuid;
    public PlayerSkills skills;
    public boolean isStaff;
    public PlayerPreferences preferences;
    public long firstJoin;
    public long lastJoin;
    public List<WardrobeSlot> wardrobe;
    
    public PlayerContainer(RankContainer rank, UUID id, PlayerSkills sk, boolean staff){
        ranks = rank;
        uuid = id;
        skills = sk;
        isStaff = staff;
        preferences = new PlayerPreferences();
        firstJoin = System.currentTimeMillis();
        lastJoin = firstJoin;
        wardrobe = new ArrayList<>();
        for(int i = 0; i < 7; i++)
            wardrobe.add(new WardrobeSlot());
    }
}
