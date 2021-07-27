package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExcavatingSkillMap extends SkillMap {

    public ExcavatingSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<ComplexReward> getRewardList() {
        ItemStack esand = SkyblockMaterial.ENCHANTED_SAND.getItem();
        esand.setAmount(32);
        ItemStack eclay = SkyblockMaterial.ENCHANTED_CLAY_BALL.getItem();
        eclay.setAmount(16);
        ItemStack egravel = SkyblockMaterial.ENCHANTED_GRAVEL.getItem();
        egravel.setAmount(16);
        ItemStack esoul = SkyblockMaterial.ENCHANTED_SOUL_SAND.getItem();
        esoul.setAmount(64);
        ItemStack ecb = SkyblockMaterial.ENCHANTED_CLAY_BLOCK.getItem();
        ecb.setAmount(8);
        ItemStack emagma = SkyblockMaterial.ENCHANTED_MAGMA_BLOCK.getItem();
        emagma.setAmount(3);

        String stat = ChatColor.GOLD+ SkyblockConstants.FORTUNE + " Excavating Fortune";
        ComplexReward stat1 = new ComplexReward(stat, 2, null, 0);
        ComplexReward stat2 = new ComplexReward(stat, 3, null, 0);
        ComplexReward stat3 = new ComplexReward(stat, 5, null, 0);
        ComplexReward stat4 = new ComplexReward(stat, 10, null, 0);
        ComplexReward item1 = new ComplexReward(stat, 3, esand, 32);
        ComplexReward item2 = new ComplexReward(stat, 3, eclay, 16);
        ComplexReward item3 = new ComplexReward(stat, 5, egravel, 16);
        ComplexReward item4 = new ComplexReward(stat, 5, esoul, 64);
        ComplexReward item5 = new ComplexReward(stat, 10, ecb, 8);
        ComplexReward item6 = new ComplexReward(stat, 25, emagma, 3);

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
