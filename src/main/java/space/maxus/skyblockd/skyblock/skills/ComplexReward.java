package space.maxus.skyblockd.skyblock.skills;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;

import java.util.Arrays;
import java.util.List;

public class ComplexReward implements SkillReward {
    private final String statName;
    private final int statValue;
    private final @Nullable ItemStack item;
    private final int itemValue;
    private final List<Boolean> claimed = Arrays.asList(false, false);

    public ComplexReward(String s, int sv, @Nullable ItemStack i, int iv){
        statName = s;
        statValue = sv;
        if(i != null) {
            ItemMeta m = i.getItemMeta();
            assert m != null;
            if(!m.getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)){
                CustomItem.toSkyblockItem(i);
            }
        }
        item = i;
        itemValue = iv;
    }

    public int getItemValue() {
        return itemValue;
    }

    public int getStatValue() {
        return statValue;
    }

    public @Nullable ItemStack getItem() {
        return item;
    }

    public String getStatName() {
        return statName;
    }
}
