package space.maxus.skyblockd.objects;

import java.util.UUID;

public class PlayerContainer{
    public RankContainer ranks;
    public UUID uuid;
    public PlayerSkills skills;
    public boolean isStaff;
    public PlayerPreferences preferences;
    public long firstJoin;
    public long lastJoin;

    public PlayerContainer(RankContainer rank, UUID id, PlayerSkills sk, boolean staff){
        ranks = rank;
        uuid = id;
        skills = sk;
        isStaff = staff;
        preferences = new PlayerPreferences();
        firstJoin = System.currentTimeMillis();
        lastJoin = firstJoin;
    }
}
