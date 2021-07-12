package space.maxus.skyblockd.skyblock.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class MappedSkill extends Skill{

    public MappedSkill(Player p) {
        SkillMap m = getMap();
        levelTable = m.getExperience();
        rewardTable = m.getRewards();
        name = m.getSkillName();
        prof = m.getProfession();
        owner = getOwner(p);
    }

    public abstract SkillMap getMap();


    public abstract String getSkyblockId();


    @Override
    public Class<? extends SkillResource> getResource() {return null;}

    public abstract Material getSkillItem();

    @Override
    public String getSkillResourceFile() {return null;}

    public abstract Player getOwner(Player p);
}
