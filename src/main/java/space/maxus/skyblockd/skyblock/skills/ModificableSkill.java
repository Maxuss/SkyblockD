package space.maxus.skyblockd.skyblock.skills;

import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.skyblock.objects.SkillModifier;

import java.util.List;

// simple interface for sorting skills
// that can be modified out of skills
// that cant be modified
public interface ModificableSkill {
    default List<Float> getModifiers(){
        JsonHelper<SkillModifier> m = new JsonHelper<>(SkillModifier.class, true);
        return m.jsonFromResource("skilltables/modifiers.json").baseModifiers;
    }

    default float getModifier(int level) {
        List<Float> mods = getModifiers();
        return mods.get(level-1);
    }
}
