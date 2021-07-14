package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CraftingSkillMap extends SkillMap {
    public CraftingSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<ComplexReward> getRewardList() {
        ComplexReward stat1 = new ComplexReward(ChatColor.AQUA+"Intelligence", 2, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.AQUA+"Intelligence", 3, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.AQUA+"Intelligence", 5, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.AQUA+"Intelligence", 10, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.AQUA+"Intelligence", 3, new ItemStack(Material.BRICK, 32), 32);
        ComplexReward item2 = new ComplexReward(ChatColor.AQUA+"Intelligence", 3, new ItemStack(Material.LEATHER, 32), 32);
        ComplexReward item3 = new ComplexReward(ChatColor.AQUA+"Intelligence", 5, new ItemStack(Material.DRAGON_BREATH, 1), 1);
        ComplexReward item4 = new ComplexReward(ChatColor.AQUA+"Intelligence", 5, new ItemStack(Material.NETHERITE_SCRAP, 5), 5);
        ComplexReward item5 = new ComplexReward(ChatColor.AQUA+"Intelligence", 10, new ItemStack(Material.NETHERITE_CHESTPLATE, 1), 1);
        ComplexReward item6 = new ComplexReward(ChatColor.AQUA+"Intelligence", 25, new ItemStack(Material.NETHERITE_BLOCK, 5), 5);

        List<ComplexReward> rewards = new ArrayList<>(Collections.nCopies(5, stat1));
        rewards.addAll(Arrays.asList(
                item1, stat2, stat2, stat2, stat2, stat2, item2,
                stat3, stat3, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, stat4, item5, stat4,
                stat4, item6
        ));

        return rewards;
    }
}
