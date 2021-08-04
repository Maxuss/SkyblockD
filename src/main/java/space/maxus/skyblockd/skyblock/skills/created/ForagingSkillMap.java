package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ForagingSkillMap extends SkillMap {
    public ForagingSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public @NotNull List<ComplexReward> getRewardList() {
        ItemStack eoak = SkyblockMaterial.ENCHANTED_OAK_LOG.getItem();
        eoak.setAmount(8);
        ItemStack ebirch = SkyblockMaterial.ENCHANTED_BIRCH_LOG.getItem();
        ebirch.setAmount(8);
        ItemStack edarkoak = SkyblockMaterial.ENCHANTED_SPRUCE_LOG.getItem();
        edarkoak.setAmount(8);
        ItemStack eacacia = SkyblockMaterial.ENCHANTED_ACACIA_LOG.getItem();
        eacacia.setAmount(8);
        ItemStack ejungle = SkyblockMaterial.ENCHANTED_JUNGLE_LOG.getItem();
        ejungle.setAmount(8);
        ItemStack hardwood = SkyblockMaterial.HARDWOOD.getItem();
        hardwood.setAmount(8);

        ComplexReward stat1 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 1, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 2, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 3, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 6, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 1, eoak, 8);
        ComplexReward item2 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 2, ebirch, 8);
        ComplexReward item3 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 3, edarkoak, 8);
        ComplexReward item4 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 6, eacacia, 8);
        ComplexReward item5 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 6, ejungle, 8);
        ComplexReward item6 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 8, hardwood, 8);

        List<ComplexReward> rewards = new ArrayList<>(Collections.nCopies(5, stat1));
        rewards.addAll(Arrays.asList(
                item1, stat2, stat2, stat2, stat2, stat2, item2,
                stat3, stat3, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, item4, stat4, item5,
                stat4, item6, stat4
        ));

        return rewards;
    }
}
