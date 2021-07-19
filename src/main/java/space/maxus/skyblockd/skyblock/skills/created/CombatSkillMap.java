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

public class CombatSkillMap extends SkillMap  {

    public CombatSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<ComplexReward> getRewardList() {
        ComplexReward stat1 = new ComplexReward(ChatColor.RED+"% more Damage", 1, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.RED+"% more Damage", 2, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.RED+"% more Damage", 3, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.RED+"% more Damage", 5, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.RED+"% more Damage", 1, new ItemStack(Material.IRON_INGOT, 32), 32);
        ComplexReward item2 = new ComplexReward(ChatColor.RED+"% more Damage", 2, new ItemStack(Material.NETHERITE_SCRAP, 64), 32);
        ComplexReward item3 = new ComplexReward(ChatColor.RED+"% more Damage", 2, new ItemStack(Material.STICK, 128), 128);
        ComplexReward item4 = new ComplexReward(ChatColor.RED+"% more Damage", 3, new ItemStack(Material.NETHERITE_INGOT, 5), 5);
        ComplexReward item5 = new ComplexReward(ChatColor.RED+"% more Damage", 5, new ItemStack(Material.NETHERITE_SWORD, 1), 1);
        ComplexReward item6 = new ComplexReward(ChatColor.RED+"% more Damage", 5, new ItemStack(Material.NETHERITE_BLOCK, 5), 5);

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
