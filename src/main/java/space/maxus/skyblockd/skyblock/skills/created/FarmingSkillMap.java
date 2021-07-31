package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FarmingSkillMap extends SkillMap {

    public FarmingSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public @NotNull List<ComplexReward> getRewardList() {
        ItemStack epork = SkyblockMaterial.ENCHANTED_PORKCHOP.getItem();
        epork.setAmount(8);
        ItemStack ebeef = SkyblockMaterial.ENCHANTED_BEEF.getItem();
        ebeef.setAmount(8);
        ItemStack erabbit = SkyblockMaterial.ENCHANTED_RABBIT.getItem();
        erabbit.setAmount(8);
        ItemStack ecarrot = SkyblockMaterial.ENCHANTED_CARROT.getItem();
        ecarrot.setAmount(8);
        ItemStack epotato = SkyblockMaterial.ENCHANTED_POTATO.getItem();
        epotato.setAmount(8);
        ItemStack ehay = SkyblockMaterial.ENCHANTED_HAY.getItem();
        ItemStack potatoes = new ItemStack(Material.POTATO, 16);
        CustomItem.toSkyblockItem(potatoes);
        ItemStack stew = SkyblockMaterial.SUPREME_STEW.getItem();

        String stat = ChatColor.GOLD+ SkyblockConstants.FORTUNE +" Farming Fortune";
        ComplexReward stat1 = new ComplexReward(stat, 3, null, 0);
        ComplexReward stat2 = new ComplexReward(stat, 5, null, 0);
        ComplexReward stat3 = new ComplexReward(stat, 7, null, 0);
        ComplexReward stat4 = new ComplexReward(stat, 10, null, 0);
        ComplexReward item0 = new ComplexReward(stat, 3, potatoes, 16);
        ComplexReward item1 = new ComplexReward(stat, 3, epork, 8);
        ComplexReward item2 = new ComplexReward(stat, 3, ebeef, 8);
        ComplexReward item3 = new ComplexReward(stat, 5, erabbit, 8);
        ComplexReward item4 = new ComplexReward(stat, 5, ecarrot, 8);
        ComplexReward item5 = new ComplexReward(stat, 7, epotato, 8);
        ComplexReward item6 = new ComplexReward(stat, 10, ehay, 1);
        ComplexReward item7 = new ComplexReward(stat, 10, stew, 1);

        List<ComplexReward> rewards = new ArrayList<>(Collections.nCopies(4, stat1));
        rewards.addAll(Arrays.asList(
                item0, item1, stat2, stat2, stat2, stat2, item2,
                stat3, stat3, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, item5, item6, stat4,
                item6, stat4, item7
        ));

        return rewards;
    }
}
