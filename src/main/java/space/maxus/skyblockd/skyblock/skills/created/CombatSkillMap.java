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

public class CombatSkillMap extends SkillMap  {

    public CombatSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public @NotNull List<ComplexReward> getRewardList() {
        ItemStack eflesh = SkyblockMaterial.ENCHANTED_ROTTEN_FLESH.getItem();
        eflesh.setAmount(32);
        ItemStack ebone = SkyblockMaterial.ENCHANTED_BONE.getItem();
        ebone.setAmount(16);
        ItemStack epowder = SkyblockMaterial.ENCHANTED_GUNPOWDER.getItem();
        epowder.setAmount(32);
        ItemStack erod = SkyblockMaterial.ENCHANTED_BLAZE_ROD.getItem();
        erod.setAmount(5);
        ItemStack emagma = SkyblockMaterial.ENCHANTED_MAGMA_CREAM.getItem();
        emagma.setAmount(32);
        ItemStack epearl = SkyblockMaterial.ENCHANTED_ENDER_PEARL.getItem();
        epearl.setAmount(32);

        ComplexReward stat1 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 1, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 2, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 3, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 5, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 1, eflesh, 32);
        ComplexReward item2 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 2, ebone, 16);
        ComplexReward item3 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 2, epowder, 32);
        ComplexReward item4 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 3, erod, 16);
        ComplexReward item5 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 5, emagma, 32);
        ComplexReward item6 = new ComplexReward(ChatColor.RED+ SkyblockConstants.STRENGTH+" Strength", 5, epearl, 5);

        List<ComplexReward> rewards = new ArrayList<>(Collections.nCopies(5, stat1));
        rewards.addAll(Arrays.asList(
                item1, stat2, stat2, stat2, stat2, stat2, item2,
                stat3, stat3, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, stat4, item5, stat4,
                stat4, item6, stat4
        ));

        return rewards;
    }
}
