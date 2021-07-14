package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.ExtendableSkill;
import space.maxus.skyblockd.skyblock.skills.ModificableSkill;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class Excavating extends ExtendableSkill implements ModificableSkill {
    public Excavating(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return SkyblockD.getMapManager().getMaps().get("excavating");
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("skill_excavating");
    }

    @Override
    public Material getSkillItem() {
        return Material.IRON_SHOVEL;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
