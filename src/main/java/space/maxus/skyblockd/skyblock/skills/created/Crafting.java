package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.ExtendableSkill;
import space.maxus.skyblockd.skyblock.skills.ModificableSkill;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class Crafting extends ExtendableSkill implements ModificableSkill {
    public Crafting(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return SkyblockD.getMapManager().getMaps().get("crafting");
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("skill_crafting");
    }

    @Override
    public Material getSkillItem() {
        return Material.ITEM_FRAME;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
