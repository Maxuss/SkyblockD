package space.maxus.skyblockd.skyblock.skills;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SimpleSkillMap extends SkillMap {
    public SimpleSkillMap(String name, String prof) { super(name, prof); }

    @Override
    public @NotNull List<SimpleReward> getRewardList() {
        return Collections.nCopies(28, new SimpleReward(ChatColor.WHITE+"Speed", 15, "GOLD_BLOCK", 2));
    }
}
