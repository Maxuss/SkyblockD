package space.maxus.skyblockd.skyblock.skills;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ComplexReward implements SkillReward {
    public String statName;
    public int statValue;
    public ItemStack item;
    public int itemValue;
    public List<Boolean> claimed = Arrays.asList(false, false);

    public ComplexReward(String s, int sv, ItemStack i, int iv){
        statName = s;
        statValue = sv;
        item = i;
        itemValue = iv;
    }

    public int getItemValue() {
        return itemValue;
    }

    public int getStatValue() {
        return statValue;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getStatName() {
        return statName;
    }
}
