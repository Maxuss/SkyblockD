package space.maxus.skyblockd.skyblock.skills;

import java.util.List;

public class StatTable {
    public List<? extends SkillReward> statValues;

    public StatTable(List<? extends SkillReward> rewards){
        statValues = rewards;
    }
}
