package space.maxus.skyblockd.skyblock.skills;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public abstract class SkillMap {
    private final @NotNull StatTable rewards;
    private final @NotNull SkillTable levels;
    private final String name;
    private final String prof;

    public abstract List<? extends SkillReward> getRewardList();

    public @NotNull StatTable getRewards() {return rewards;}
    public @NotNull SkillTable getExperience() {return levels;}
    public String getSkillName() {return name; }
    public String getProfession() {return prof; }

    public SkillMap(String name, String prof){
        rewards = new StatTable(getRewardList());
        // 28 levels total
        levels = new SkillTable(Arrays.asList(
                100, 250, 400, 700, 1000, 1500, 2000,
                3000, 5000, 7500, 10000, 15000, 20000,
                25000, 30000, 40000, 45000, 57000,
                70000, 90000, 120000, 150000, 200000,
                250000, 300000, 350000, 400000, 500000,
                800000
        ));
        this.name = name;
        this.prof = prof;
    }
}
