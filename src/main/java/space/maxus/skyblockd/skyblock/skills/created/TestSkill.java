package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.MappedSkill;
import space.maxus.skyblockd.skyblock.skills.SimpleSkillMap;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class TestSkill extends MappedSkill {

    public TestSkill(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return new SimpleSkillMap("Gaming", "Gamer");
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("test_skill");
    }

    @Override
    public Material getSkillItem() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
