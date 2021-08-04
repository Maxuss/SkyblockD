package space.maxus.skyblockd.skyblock.skills.created;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
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
    public @NotNull List<ComplexReward> getRewardList() {
        ItemStack ecomb = SkyblockMaterial.ENCHANTED_HONEYCOMB.getItem();
        ecomb.setAmount(32);
        ItemStack hardwood = SkyblockMaterial.HARDWOOD.getItem();
        hardwood.setAmount(4);
        ItemStack emem = SkyblockMaterial.ENCHANTED_PHANTOM_MEMBRANE.getItem();
        emem.setAmount(16);
        ItemStack ewisp = SkyblockMaterial.ENCHANTED_WISP.getItem();
        ItemStack eblock = SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK.getItem();
        ItemStack estar = SkyblockMaterial.ENCHANTED_NETHER_STAR.getItem();

        ComplexReward stat1 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 2, null, 0);
        ComplexReward stat2 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, null, 0);
        ComplexReward stat3 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 5, null, 0);
        ComplexReward stat4 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 10, null, 0);
        ComplexReward item1 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, ecomb, 32);
        ComplexReward item2 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 3, hardwood, 4);
        ComplexReward item3 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 5, emem, 16);
        ComplexReward item4 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 5, ewisp, 1);
        ComplexReward item5 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 10, eblock, 1);
        ComplexReward item6 = new ComplexReward(ChatColor.LIGHT_PURPLE+"๑ Ability Damage", 25, estar, 1);

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
