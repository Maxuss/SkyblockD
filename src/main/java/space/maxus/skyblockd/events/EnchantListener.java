package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.List;
import java.util.Random;

public class EnchantListener extends BetterListener {
    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
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

        String sxp = String.valueOf(exp).replace(",", ".");

        String rawCommand = "title "+p.getName()+" actionbar {\"text\":\"+"+sxp+" Mysticism Experience\", \"color\":\"dark_aqua\"}";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rawCommand);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);

        pc.skills.totalExp += exp;

        mysticism.totalExp += exp;
        mysticism.levelExp += exp;
        int toNext = SkyblockD.getMapManager().getMaps().get("mysticism").getExperience().table
                .get(mysticism.currentLevel + 1);
        int div = mysticism.levelExp - toNext;
        if(div >= 0) {
            mysticism.levelExp = div;
            p.sendMessage(new String[]{
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------",
                            ChatColor.YELLOW + "" + ChatColor.BOLD + "MYSTICISM LEVEL UP!",
                            " ",
                            ChatColor.GREEN + "You are now mysticism level " + (mysticism.currentLevel + 1) + "!",
                            ChatColor.GREEN + "Check out new level rewards in Skyblock Menu!",
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------"
                    }
            );
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
            int level = mysticism.currentLevel;
            mysticism.currentLevel++;
            String levl = "unlocked."+level;
            mysticism.collectedRewards.put(levl, true);
            ContainerHelper.updatePlayers();
            setPlayer(pc, p);
        }
    }

    private void setPlayer(PlayerContainer p, Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }
}
