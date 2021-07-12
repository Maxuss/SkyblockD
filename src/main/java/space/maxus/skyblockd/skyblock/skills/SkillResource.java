package space.maxus.skyblockd.skyblock.skills;

public class SkillResource {
    public String skillName;
    public String profession;
    public SkillTable levelTable;
    public StatTable statRewards;

    public SkillResource(String name, String prof, SkillTable table, StatTable stats){
        skillName = name;
        profession = prof;
        levelTable = table;
        statRewards = stats;
    }

    public SkillResource(SkillMap map){
        skillName = map.getSkillName();
        profession = map.getProfession();
        levelTable = map.getExperience();
        statRewards = map.getRewards();
    }

    public String getSkillName() {
        return skillName;
    }

    public SkillTable getLevelTable() {
        return levelTable;
    }

    public String getProfession() {
        return profession;
    }

    public StatTable getStatRewards() {
        return statRewards;
    }
}
