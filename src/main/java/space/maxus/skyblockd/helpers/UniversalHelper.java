package space.maxus.skyblockd.helpers;

import com.google.gson.reflect.TypeToken;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.nms.NMSColor;
import space.maxus.skyblockd.nms.PacketUtils;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.items.ArmorSet;
import space.maxus.skyblockd.skyblock.skills.ComplexReward;
import space.maxus.skyblockd.skyblock.skills.SkillMap;
import space.maxus.skyblockd.util.Roman;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversalHelper {
    public static <T> List<T> filter(@NotNull List<T> input, Predicate<? super T> filter){
        return input.stream().filter(filter).collect(Collectors.toList());
    }

    public static <T> Type getType(T in){
        return new TypeToken<T>(){}.getType();
    }

    public static void skillLevelupMessage(int newLevel, @NotNull String skillName, @NotNull SkillMap rewardMap, @NotNull Player p) {
        ArrayList<String> messages = new ArrayList<>();
        String upMsg = ChatColor.AQUA+""+ChatColor.BOLD+" SKILL LEVEL UP "+ChatColor.RESET+ChatColor.DARK_AQUA+cap(skillName)+" "+(newLevel>1 ? ChatColor.DARK_GRAY+""+new Roman(newLevel-1) + " -> "+ChatColor.DARK_AQUA+new Roman(newLevel) : ChatColor.DARK_AQUA+""+new Roman(newLevel));
        messages.add(ChatColor.DARK_AQUA+"----------------------------------------------");
        messages.add(upMsg);
        messages.add(" ");
        messages.add(ChatColor.GREEN+""+ChatColor.BOLD+" REWARDS");
        String prof = rewardMap.getProfession();
        messages.add(ChatColor.YELLOW+"  "+cap(prof)+" "+new Roman(newLevel));
        @SuppressWarnings("unchecked")
        List<ComplexReward> rewards = (List<ComplexReward>) rewardMap.getRewardList();
        boolean below = (newLevel-1)<0;
        ComplexReward newReward = rewards.get(below ? newLevel : newLevel-1);
        if(below) {
            String statMsg = ChatColor.GRAY+"   Grants "+ ChatColor.GREEN+ newReward.getStatValue() + newReward.getStatName();
            messages.add(statMsg);
        } else {
            int previousCombined = 0;
            for(int i = newLevel - 1; i >= 0; i--) {
                previousCombined += rewards.get(i).getStatValue();
            }
            String stat = newReward.getStatName();
            String statColor = stat.replace(ChatColor.stripColor(stat), "");
            String statMsg = ChatColor.GRAY+"   Grants "+ ChatColor.DARK_GRAY+previousCombined+" -> "+statColor+ (previousCombined+newReward.getStatValue()) + " " + stat;
            messages.add(statMsg);
        }
        if(newReward.getItem() != null && newReward.getItemValue() > 0) {
            assert newReward.getItem().getItemMeta() != null;
            boolean oneItem = newReward.getItemValue() == 1;
            String itemName = newReward.getItem().getItemMeta().getDisplayName();
            String itemColor = itemName.replace(ChatColor.stripColor(itemName), "");
            String an = "eaiouEAIOU".indexOf(ChatColor.stripColor(itemName).charAt(0)) >= 0 ? "an " : "a ";
            String itemMsg = ChatColor.GRAY+"   Additional reward: " + itemColor + (oneItem ? an : newReward.getItemValue()+"x ") + itemName;
            messages.add(itemMsg);
        }
        messages.add(" ");
        messages.add(ChatColor.GRAY+" Check out these rewards in your "+ChatColor.YELLOW+"Skyblock Menu"+ChatColor.GRAY+"!");
        messages.add(ChatColor.DARK_AQUA+"----------------------------------------------");
        String[] array = messages.toArray(new String[0]);
        p.sendMessage(array);
    }

    public static void giveSkillExperience(@NotNull Player p, @NotNull String skill, int exp) {
        SkillMap map = SkyblockD.getMapManager().getMaps().get(skill.toLowerCase(Locale.ENGLISH));
        PlayerContainer pc = ContainerHelper.getPlayer(p);
        SkillContainer skc = pc.skills.data.get(skill.toLowerCase(Locale.ENGLISH));

        double d = Math.floor(exp);

        String sxp = String.valueOf(d).replace(",", ".");

        PacketUtils.sendActionbar(p, "+"+sxp+" "+cap(skill)+" Experience", NMSColor.DARK_AQUA);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);

        pc.skills.totalExp += exp;

        skc.totalExp += exp;
        skc.levelExp += exp;
        if(skc.currentLevel+1 < 28) {
            int toNext = map.getExperience().table.get(skc.currentLevel + 1);
            int div = skc.levelExp - toNext;
            if (div >= 0) {
                skc.levelExp = div;
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
                int level = skc.currentLevel;
                UniversalHelper.skillLevelupMessage(level + 1, skill, map, p);
                skc.currentLevel++;
                String levl = "unlocked." + level;
                skc.collectedRewards.put(levl, true);
                ContainerHelper.updatePlayers();
                setPlayer(pc, p);
            }
        }
    }

    public static void setPlayer(PlayerContainer p, @NotNull Player pl){
        List<PlayerContainer> conts = UniversalHelper.filter(SkyblockD.getPlayers(), c -> c.uuid.equals(pl.getUniqueId()));
        SkyblockD.players.remove(conts.get(conts.size() - 1));
        SkyblockD.players.add(p);
        ContainerHelper.updatePlayers();
    }

    public static int getStatFromSkill(String skill, @NotNull Player p) {
        PlayerContainer c = ContainerHelper.getPlayer(p);
        SkillContainer skc = c.skills.data.get(skill);
        int level = skc.currentLevel;
        if(level <= 0) return 0;
        SkillMap map = SkyblockD.getMapManager().getMaps().get(skill);
        @SuppressWarnings("unchecked")
        List<ComplexReward> rewards = (List<ComplexReward>) map.getRewardList();
        int total = 0;
        for(int i = level; i > 0; i--) {
            total += rewards.get(i).getStatValue();
        }
        return total;
    }

    public static int getMiningFortune(@NotNull Player p) {
        int total = getStatFromSkill("mining", p);
        total += ItemHelper.getStatFromItems(p, "miningFortune");
        return total;
    }

    public static int getFarmingFortune(@NotNull Player p) {
        int total = getStatFromSkill("farming", p);
        total += ItemHelper.getStatFromItems(p, "farmingFortune");
        return total;
    }

    public static int getExcavatingFortune(@NotNull Player p) {
        int total = getStatFromSkill("excavating", p);
        total += ItemHelper.getStatFromItems(p, "excavatingFortune");
        return total;
    }

    public static int getStrength(@NotNull Player p) {
        int total = getStatFromSkill("foraging", p);
        total += getStatFromSkill("combat", p);
        total += ItemHelper.getStatFromItems(p, "strength");
        return total;
    }

    public static int getSeaCreatureChance(@NotNull Player p) {
        int total = getStatFromSkill("fishing", p);
        total += ItemHelper.getStatFromItems(p, "scc");
        return total;
    }

    public static int getAbilityDamage(@NotNull Player p) {
        int total = getStatFromSkill("crafting", p);
        total += getStatFromSkill("mysticism", p);
        total += ItemHelper.getStatFromItems(p, "abilDamage");
        return total;
    }

    public static boolean checkFullSet(@NotNull List<ItemStack> toTest, @NotNull Player p) {
        PlayerInventory inv = p.getInventory();
        boolean a = toTest.get(0).isSimilar(inv.getBoots());
        boolean b = toTest.get(1).isSimilar(inv.getLeggings());
        boolean c = toTest.get(2).isSimilar(inv.getChestplate());
        boolean d = toTest.get(3).isSimilar(inv.getHelmet());
        return a && b && c && d;
    }

    public static boolean checkFullSet(@NotNull ArmorSet set, Player p) {
        PlayerInventory inv = p.getInventory();
        boolean a = set.getBoots().isSimilar(inv.getBoots());
        boolean b = set.getLeggings().isSimilar(inv.getLeggings());
        boolean c = set.getChestplate().isSimilar(inv.getChestplate());
        boolean d = set.getHelmet().isSimilar(inv.getHelmet());
        return a && b && c && d;
    }

    public static boolean setHasKey(@NotNull NamespacedKey key, @NotNull PersistentDataType<?, ?> type, @NotNull Player p) {
        List<ItemStack> contents = Arrays.asList(p.getInventory().getArmorContents());
        if(contents.isEmpty()) return false;
        return contents.stream().allMatch(item -> {
            if(item == null || !item.hasItemMeta()) return false;
            return Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().has(key, type);
        });
    }

    private static @NotNull String cap(@NotNull String s) { return CustomItem.capitalize(s); }
}
