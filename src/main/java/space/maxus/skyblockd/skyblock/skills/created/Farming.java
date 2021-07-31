package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.ExtendableSkill;
import space.maxus.skyblockd.skyblock.skills.ModificableSkill;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class Farming extends ExtendableSkill implements ModificableSkill {
    public Farming(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return SkyblockD.getMapManager().getMaps().get("farming");
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("skill_farming");
    }

    @Override
    public @NotNull Material getSkillItem() {
        return Material.IRON_HOE;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
