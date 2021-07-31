package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.objects.SkillModifier;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SkillHelper {
    public static List<Float> getModifiers(){
        SkillModifier modifiers = SkyblockD.getServerData().modifier;
        return modifiers.baseModifiers;
    }

    public static float getModifier(int level) {
        List<Float> mods = getModifiers();
        return mods.get(level-1);
    }

    public static int getExpForSkill(@NotNull Material item, @NotNull String skill) {
        HashMap<String, HashMap<String, Integer>> datas = new HashMap<String, HashMap<String, Integer>>() {
            {
                put("mining", SkyblockD.getServerData().mining);
                put("farming", SkyblockD.getServerData().farming);
                put("foraging", SkyblockD.getServerData().foraging);
                put("excavating", SkyblockD.getServerData().excavating);
            }
        };
        return datas.get(skill.toLowerCase(Locale.ENGLISH)).get(item.name());
    }
}
