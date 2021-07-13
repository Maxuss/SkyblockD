package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.skyblock.skills.SimpleReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MiningSkillMap extends SkillMap {
    public MiningSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<SimpleReward> getRewardList() {
        SimpleReward stat1 = new SimpleReward(ChatColor.GREEN+"Defense", 2, "", 0);
        SimpleReward stat2 = new SimpleReward(ChatColor.GREEN+"Defense", 3, "", 0);
        SimpleReward stat3 = new SimpleReward(ChatColor.GREEN+"Defense", 5, "", 0);
        SimpleReward stat4 = new SimpleReward(ChatColor.GREEN+"Defense", 10, "", 0);
        SimpleReward item1 = new SimpleReward(ChatColor.GREEN+"Defense", 3, "GOLD_BLOCK", 1);
        SimpleReward item2 = new SimpleReward(ChatColor.GREEN+"Defense", 3, "DIAMOND_BLOCK", 2);
        SimpleReward item3 = new SimpleReward(ChatColor.GREEN+"Defense", 5, "NETHERITE_SCRAP", 10);
        SimpleReward item4 = new SimpleReward(ChatColor.GREEN+"Defense", 5, "NETHERITE_INGOT", 2);
        SimpleReward item5 = new SimpleReward(ChatColor.GREEN+"Defense", 10, "NETHERITE_BLOCK", 1);
        SimpleReward item6 = new SimpleReward(ChatColor.GREEN+"Defense", 25, "NETHERITE_PICKAXE", 5);

        List<SimpleReward> rewards = new ArrayList<>(Collections.nCopies(5, stat1));
        rewards.addAll(Arrays.asList(
                item1, stat2, stat2, stat2, stat2, stat2, item2,
                stat3, stat3, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, stat4, item5, stat4,
                stat4, item6
        ));


        return rewards;
    }
}
