package xyz.voidmoment.skyblockd.objects;

public class RankContainer {
    public String uuid;
    public String username;
    public String rankGroup = "rank.none";

    public RankContainer(String id, String name){
        uuid = id;
        username = name;
    }
}
