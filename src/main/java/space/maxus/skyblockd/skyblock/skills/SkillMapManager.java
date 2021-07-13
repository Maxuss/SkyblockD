package space.maxus.skyblockd.skyblock.skills;

import java.util.HashMap;

public class SkillMapManager {
    private final HashMap<String, SkillMap> maps;

    public HashMap<String, SkillMap> getMaps() {return maps;}

    public void addMap(String skillName, SkillMap map){
        maps.put(skillName, map);
    }

    public SkillMapManager() {
        maps = new HashMap<>();
    }
}
