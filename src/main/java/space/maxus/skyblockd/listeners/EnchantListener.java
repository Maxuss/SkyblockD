package space.maxus.skyblockd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.List;
import java.util.Random;

public class EnchantListener extends BetterListener {
    @EventHandler
    public void onEnchant(@NotNull EnchantItemEvent e) {
        int cost = e.getExpLevelCost();
        Player p = e.getEnchanter();

        List<PlayerContainer> players = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
        PlayerContainer pc = players.get(players.size()-1);

        SkillContainer mysticism = pc.skills.data.get("mysticism");
        int lvl = mysticism.currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;
        float modifier = SkillHelper.getModifier(tlvl);

        float baseExp = cost / new Random().nextFloat();
        float exp = modifier * baseExp;

        UniversalHelper.giveSkillExperience(p, "mysticism", Math.round(exp));
    }
}
