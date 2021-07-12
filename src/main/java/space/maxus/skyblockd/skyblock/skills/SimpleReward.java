package space.maxus.skyblockd.skyblock.skills;

public class SimpleReward implements SkillReward {
    public String statName;
    public int statValue;
    public String itemName;
    public int itemValue;

    public SimpleReward(String s, int sv, String i, int iv){
        statName = s;
        statValue = sv;
        itemName = i;
        itemValue = iv;
    }

    public int getItemValue() {
        return itemValue;
    }

    public int getStatValue() {
        return statValue;
    }

    public String getItemName() {
        return itemName;
    }

    public String getStatName() {
        return statName;
    }
}
