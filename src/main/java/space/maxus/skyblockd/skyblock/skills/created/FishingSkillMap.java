package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.skills.SkillReward;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static space.maxus.skyblockd.skyblock.utility.SkyblockConstants.SCC;

public class FishingSkillMap extends SkillMap {
    public FishingSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<? extends SkillReward> getRewardList() {
        ItemStack ecod = SkyblockMaterial.ENCHANTED_COD.getItem();
        ecod.setAmount(5);
        ItemStack eccod = SkyblockMaterial.ENCHANTED_COOKED_COD.getItem();
        ecod.setAmount(2);
        ItemStack esalm = SkyblockMaterial.ENCHANTED_SALMON.getItem();
        ecod.setAmount(5);
        ItemStack ecsalm = SkyblockMaterial.ENCHANTED_COOKED_SALMON.getItem();
        ecod.setAmount(2);
        ItemStack espong = SkyblockMaterial.ENCHANTED_SPONGE.getItem();
        espong.setAmount(5);
        ItemStack eprism = SkyblockMaterial.ENCHANTED_DARK_PRISMARINE.getItem();
        eprism.setAmount(5);

        ComplexReward stat1 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 2, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 3, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 5, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 6, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 2, ecod, 5);
        ComplexReward item2 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 2, esalm, 5);
        ComplexReward item3 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 3, espong, 5);
        ComplexReward item4 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 3, eccod, 2);
        ComplexReward item5 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 5, ecsalm, 2);
        ComplexReward item6 = new ComplexReward(ChatColor.AQUA+SCC + " Sea Creature Chance", 6, eprism, 5);

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
