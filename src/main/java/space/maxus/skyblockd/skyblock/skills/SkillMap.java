package space.maxus.skyblockd.skyblock.skills;

import java.util.Arrays;
import java.util.List;

public abstract class SkillMap {
    public abstract List<SimpleReward> getRewardList();

    private final StatTable rewards;
    private final SkillTable levels;
    private final String name;
    private final String prof;

    public StatTable getRewards() {return rewards;}
    public SkillTable getExperience() {return levels;}
    public String getSkillName() {return name; }
    public String getProfession() {return prof; }

    public SkillMap(String name, String prof){
        rewards = new StatTable(getRewardList());
        // 28 levels total
        levels = new SkillTable(Arrays.asList(
                10, 50, 100, 200, 250, 300, 325,
                400, 500, 700, 1000, 1200, 1500,
                2000, 2500, 3500, 5000, 7000,
                10000, 12000, 15000, 20000, 22000,
                30000, 40000, 50000, 70000, 89000
        ));
        this.name = name;
        this.prof = prof;
    }
}
