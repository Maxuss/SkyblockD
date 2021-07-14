package space.maxus.skyblockd.objects;

import space.maxus.skyblockd.SkyblockD;

import java.util.HashMap;

public class PlayerSkills {
    public HashMap<String, SkillContainer> data;

    public int totalExp;

    public static final PlayerSkills EMPTY = new PlayerSkills(SkyblockD.getConsts().getBaseSkillMap(), 0);

    public PlayerSkills(HashMap<String,SkillContainer> skilldata, int total){
        data = skilldata;
        totalExp = total;
    }

}
