package space.maxus.skyblockd.skyblock.utility;

import org.bukkit.Material;
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

    public static int getExpForSkill(Material item, String skill) {
        HashMap<String, HashMap<String, Integer>> datas = new HashMap<String, HashMap<String, Integer>>() {
            {
                put("mining", SkyblockD.getServerData().mining);
                put("farming", SkyblockD.getServerData().farming);
                put("foraging", SkyblockD.getServerData().foraging);
            }
        };
        SkyblockD.logger.info(skill.toLowerCase(Locale.ENGLISH));
        return datas.get(skill.toLowerCase(Locale.ENGLISH)).get(item.name());
    }
}
