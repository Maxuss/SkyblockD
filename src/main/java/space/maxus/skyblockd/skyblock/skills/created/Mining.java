package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.MappedSkill;
import space.maxus.skyblockd.skyblock.skills.ModificableSkill;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class Mining extends MappedSkill implements ModificableSkill {
    public Mining(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return SkyblockD.getMapManager().getMaps().get("mining");
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("skill_mining");
    }

    @Override
    public Material getSkillItem() {
        return Material.IRON_PICKAXE;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
