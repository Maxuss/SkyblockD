package space.maxus.skyblockd.objects;

import java.util.HashMap;

public class SkillContainer {
    public int totalExp;
    public int currentLevel;
    public int levelExp;
    public String skillName;
    public HashMap<String, Boolean> collectedRewards;

    public SkillContainer(int total, int cur, int level, String name){
        totalExp = total;
        currentLevel = cur;
        levelExp = level;
        skillName = name;
        collectedRewards = new HashMap<>();
        for(int i = 0; i < 28; i++) {
            collectedRewards.put("collected."+i, false);
            collectedRewards.put("unlocked."+i, false);
        }
    }
}
