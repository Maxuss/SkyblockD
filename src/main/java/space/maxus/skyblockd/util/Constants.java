package space.maxus.skyblockd.util;

import org.bukkit.ChatColor;
import space.maxus.skyblockd.objects.SkillContainer;

import java.util.HashMap;

public class Constants {
    private final HashMap<Integer, ChatColor> damageMap;
    private final HashMap<String, SkillContainer> baseSkillMap;

    public HashMap<Integer, ChatColor> getDamageMap() {
        return damageMap;
    }

    public HashMap<String, SkillContainer> getBaseSkillMap() { return baseSkillMap; }

    public Constants() {
        damageMap = new HashMap<Integer, ChatColor>() {
            {
                put(0, ChatColor.WHITE);
                put(1, ChatColor.YELLOW);
                put(2, ChatColor.YELLOW);
                put(3, ChatColor.GOLD);
                put(4, ChatColor.RED);
                put(5, ChatColor.RED);
                put(6, ChatColor.GOLD);
                put(7, ChatColor.YELLOW);
                put(8, ChatColor.YELLOW);
                put(9, ChatColor.WHITE);
            }
        };

        baseSkillMap = new HashMap<String, SkillContainer>() {
            {
                put("mining", new SkillContainer(0, 0, 0, "mining"));
                put("farming", new SkillContainer(0, 0, 0, "farming"));
                put("foraging", new SkillContainer(0, 0, 0, "foraging"));
                put("combat", new SkillContainer(0, 0, 0, "combat"));
                put("excavating", new SkillContainer(0, 0, 0, "excavating"));
                put("mysticism", new SkillContainer(0, 0, 0, "mysticism"));
                put("crafting", new SkillContainer(0, 0, 0, "crafting"));
                put("fishing", new SkillContainer(0, 0, 0, "fishing"));
            }
        };
    }
}
