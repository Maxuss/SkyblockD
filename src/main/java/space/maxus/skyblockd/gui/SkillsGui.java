package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.util.Roman;

import java.util.*;

public class SkillsGui extends InventoryBase {
    private Player player;

    public void setPlayer(Player p) {
        player = p;
    }

    @Override
    public @NotNull String getName() {
        return "Your Skills";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public InventoryHolder getHolder(Player p) {return p;}

    @Override
    public @NotNull Inventory generateContains(@NotNull Inventory base) {
        List<PlayerContainer> pcs = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(player.getUniqueId()));

        PlayerContainer pc = pcs.get(pcs.size()-1);

        HashMap<String, SkillContainer> sd = pc.skills.data;

        SkyblockD.players.sort(Comparator.comparing(cont -> cont.skills.totalExp));

        int position = SkyblockD.players.indexOf(pc) + 1;

        int combatLevel = sd.get("combat").currentLevel;
        int foragingLevel = sd.get("foraging").currentLevel;
        int excavatingLevel = sd.get("excavating").currentLevel;
        int miningLevel = sd.get("mining").currentLevel;
        int mysticismLevel = sd.get("mysticism").currentLevel;
        int craftingLevel = sd.get("crafting").currentLevel;
        int farmingLevel = sd.get("farming").currentLevel;
        int fishingLevel = sd.get("fishing").currentLevel;

        float asl = ItemHelper.round((combatLevel+foragingLevel+excavatingLevel
                +miningLevel+mysticismLevel+craftingLevel+farmingLevel+fishingLevel) / 8f);

        List<Integer> ints = SkyblockD.getMapManager().getMaps().get("mining").getExperience().table;

        ItemStack combat = GuiHelper.genSimpleMenuItem("Combat", Material.IRON_SWORD,
                Arrays.asList(ChatColor.GRAY+"Increase your combat level by", ChatColor.GRAY+"killing mobs.",
                " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(combatLevel),
                ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(combatLevel, ints, "combat", sd),
                ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("combat").totalExp+ " EXP"
                ));
        ItemStack foraging = GuiHelper.genSimpleMenuItem("Foraging", Material.OAK_SAPLING,
                Arrays.asList(ChatColor.GRAY+"Increase your foraging level by", ChatColor.GRAY+"chopping down trees.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(foragingLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(foragingLevel, ints, "foraging", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("foraging").totalExp+ " EXP"
                ));
        ItemStack excavating = GuiHelper.genSimpleMenuItem("Excavating", Material.DIAMOND_SHOVEL,
                Arrays.asList(ChatColor.GRAY+"Increase your excavating level by digging", ChatColor.GRAY+"dirt and finding artefacts.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(excavatingLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(excavatingLevel, ints, "excavating", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("excavating").totalExp+ " EXP"
                ));
        ItemStack mining = GuiHelper.genSimpleMenuItem("Mining", Material.STONE_PICKAXE,
                Arrays.asList(ChatColor.GRAY+"Increase your mining level by", ChatColor.GRAY+"mining various rocks and minerals.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(miningLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(miningLevel, ints, "mining", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("mining").totalExp+ " EXP"
                ));
        ItemStack mysticism = GuiHelper.genSimpleMenuItem("Mysticism", Material.GLOWSTONE_DUST,
                Arrays.asList(ChatColor.GRAY+"Increase your Mysticism level by", ChatColor.GRAY+"brewing elixirs and enchanting items.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(mysticismLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(mysticismLevel, ints, "mysticism", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("mysticism").totalExp+ " EXP"
                ));
        ItemStack crafting = GuiHelper.genSimpleMenuItem("Crafting", Material.CRAFTING_TABLE,
                Arrays.asList(ChatColor.GRAY+"Increase your crafting level by", ChatColor.GRAY+"crafting and smelting ingredients.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(craftingLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(craftingLevel, ints, "crafting", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("crafting").totalExp+ " EXP"
                ));
        ItemStack farming = GuiHelper.genSimpleMenuItem("Farming", Material.GOLDEN_HOE,
                Arrays.asList(ChatColor.GRAY+"Increase your farming level by", ChatColor.GRAY+"harvesting and processing plants.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(farmingLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(farmingLevel, ints, "farming", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("farming").totalExp + " EXP"
                ));
        ItemStack fishing = GuiHelper.genSimpleMenuItem("Fishing", Material.FISHING_ROD,
                Arrays.asList(ChatColor.GRAY+"Increase your fishing level by", ChatColor.GRAY+"fishing ...fish... and luring sea creatures.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + new Roman(fishingLevel),
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + isMax(fishingLevel, ints, "fishing", sd),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("fishing").totalExp + " EXP"
                ));

        ItemStack gls = GuiHelper.getMenuGlass();

        ItemStack back = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Go back", Material.ARROW, Collections.emptyList());

        ItemStack total = GuiHelper.getPlayerHead(player);
        ItemMeta meta = total.getItemMeta();
        assert meta != null;
        meta.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        meta.setDisplayName(ChatColor.YELLOW+"Your skills");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Total experience: "+ChatColor.GOLD+pc.skills.totalExp+" EXP",
                ChatColor.DARK_GRAY+"Global Skills leaderboard: "+ ChatColor.GOLD+position,
                ChatColor.DARK_GRAY+"Averagel level: "+ChatColor.GOLD+asl));
        total.setItemMeta(meta);

        ItemStack[] contents = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, gls, gls, gls, total, gls, gls, gls, gls,
                gls, gls, combat, foraging, mysticism, crafting, excavating, gls, gls,
                gls, gls, gls, mining,farming, fishing, gls, gls, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                back, gls, gls, gls, gls, gls, gls, gls, gls,
        };

        base.setContents(contents);

        return base;
    }

    @Override
    public @NotNull String getId() {
        return SkyblockD.getNamespace("inventory_skills");
    }

    private @NotNull String isMax(int level, @NotNull List<Integer> ints, String name, @NotNull HashMap<String, SkillContainer> sd) {
        return level >= 28 ? "MAX" : (ints.get(level+1)-sd.get(name).levelExp)+ " EXP";
    }
}
