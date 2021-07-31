package space.maxus.skyblockd.skyblock.skills;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SkillMapManager {
    private final @NotNull HashMap<String, SkillMap> maps;

    public @NotNull HashMap<String, SkillMap> getMaps() {return maps;}

    public void addMap(String skillName, SkillMap map){
        maps.put(skillName, map);
    }

    public SkillMapManager() {
        maps = new HashMap<>();
    }
}
