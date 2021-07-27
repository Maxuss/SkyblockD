package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MysticismSkillMap extends SkillMap {

    public MysticismSkillMap(String name, String prof) {
        super(name, prof);
    }

    @Override
    public List<ComplexReward> getRewardList() {
        ItemStack equartz = SkyblockMaterial.ENCHANTED_QUARTZ.getItem();
        equartz.setAmount(8);
        ItemStack powder = SkyblockMaterial.TRANSMUTATION_POWDER.getItem();
        powder.setAmount(4);
        ItemStack ewing = SkyblockMaterial.ENCHANTED_PHANTOM_WING.getItem();
        ewing.setAmount(1);
        ItemStack essence = SkyblockMaterial.ASCENDED_ESSENCE.getItem();
        essence.setAmount(1);
        ItemStack singular = SkyblockMaterial.DRAGON_SINGULARITY.getItem();
        singular.setAmount(1);
        ItemStack echorus = SkyblockMaterial.ENCHANTED_CHORUS_FLOWER.getItem();
        echorus.setAmount(16);

        ComplexReward stat1 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 5, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 7, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 10, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, equartz, 8);
        ComplexReward item2 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, powder, 4);
        ComplexReward item3 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 5, ewing, 1);
        ComplexReward item4 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 7, essence, 1);
        ComplexReward item5 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 10, singular, 1);
        ComplexReward item6 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 25, echorus, 16);

        List<ComplexReward> rewards = new ArrayList<>(Collections.nCopies(5, stat1));
        rewards.addAll(Arrays.asList(
                stat2, item1, stat2, stat2, item1, stat2, item2,
                stat3, item2, stat3, item3, stat3, item3, stat3,
                item4, stat3, item4, stat4, stat4, item5, stat4,
                item5, item6
        ));

        return rewards;
    }
}
