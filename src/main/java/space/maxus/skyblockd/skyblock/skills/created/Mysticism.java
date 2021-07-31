package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.skills.ExtendableSkill;
import space.maxus.skyblockd.skyblock.skills.ModificableSkill;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

public class Mysticism extends ExtendableSkill implements ModificableSkill {
    public Mysticism(Player p) {
        super(p);
    }

    @Override
    public SkillMap getMap() {
        return SkyblockD.getMapManager().getMaps().get("mysticism");
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("skill_mysticism");
    }

    @Override
    public @NotNull Material getSkillItem() {
        return Material.ENDER_EYE;
    }

    @Override
    public Player getOwner(Player p) {
        return p;
    }
}
