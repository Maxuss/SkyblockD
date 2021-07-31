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

public class MiningSkillMap extends SkillMap {
    public MiningSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public @NotNull List<ComplexReward> getRewardList() {
        ItemStack ecobble = SkyblockMaterial.ENCHANTED_COBBLESTONE.getItem();
        ecobble.setAmount(16);
        ItemStack rockPile = SkyblockMaterial.ENCHANTED_ROCK_PILE.getItem();
        ItemStack eiron = SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem();
        eiron.setAmount(6);
        ItemStack escrap = SkyblockMaterial.ENCHANTED_NETHERITE_SCRAP.getItem();
        escrap.setAmount(8);
        ItemStack elapis = SkyblockMaterial.ENCHANTED_LAPIS_BLOCK.getItem();
        ItemStack gemstone = SkyblockMaterial.GEMSTONE.getItem();
        gemstone.setAmount(8);

        String stat = ChatColor.GOLD+SkyblockConstants.FORTUNE+" Mining Fortune";
        ComplexReward stat1 = new ComplexReward(stat, 2, null, 0);
        ComplexReward stat2 = new ComplexReward(stat, 3, null, 0);
        ComplexReward stat3 = new ComplexReward(stat, 5, null, 0);
        ComplexReward stat4 = new ComplexReward(stat, 10, null, 0);
        ComplexReward item1 = new ComplexReward(stat, 3, ecobble, 16);
        ComplexReward item2 = new ComplexReward(stat, 3, rockPile, 1);
        ComplexReward item3 = new ComplexReward(stat, 5, eiron, 6);
        ComplexReward item4 = new ComplexReward(stat, 5, escrap, 8);
        ComplexReward item5 = new ComplexReward(stat, 10, elapis, 1);
        ComplexReward item6 = new ComplexReward(stat, 25, gemstone, 5);

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
