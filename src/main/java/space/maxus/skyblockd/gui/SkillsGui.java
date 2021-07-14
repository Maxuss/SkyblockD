package space.maxus.skyblockd.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SkillsGui extends InventoryBase {
    private Player player;

    public void setPlayer(Player p) {
        player = p;
    }

    @Override
    public String getName() {
        return "Your Skills";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public InventoryHolder getHolder(Player p) {return p;}

    @Override
    public Inventory generateContains(Inventory base) {
        List<PlayerContainer> pcs = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(player.getUniqueId()));

        PlayerContainer pc = pcs.get(pcs.size()-1);

        HashMap<String, SkillContainer> sd = pc.skills.data;

        int combatLevel = sd.get("combat").currentLevel;
        int foragingLevel = sd.get("foraging").currentLevel;
        int excavatingLevel = sd.get("excavating").currentLevel;
        int miningLevel = sd.get("mining").currentLevel;
        int mysticismLevel = sd.get("mysticism").currentLevel;
        int craftingLevel = sd.get("crafting").currentLevel;
        int farmingLevel = sd.get("farming").currentLevel;

        List<Integer> ints = SkyblockD.getMapManager().getMaps().get("mining").getExperience().table;

        ItemStack combat = GuiHelper.genSimpleMenuItem("Combat", Material.IRON_SWORD,
                Arrays.asList(ChatColor.GRAY+"Increase your combat level by", ChatColor.GRAY+"killing mobs.",
                " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + combatLevel,
                ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(combatLevel+1)-sd.get("combat").levelExp),
                ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("combat").totalExp
                ));
        ItemStack foraging = GuiHelper.genSimpleMenuItem("Foraging", Material.OAK_SAPLING,
                Arrays.asList(ChatColor.GRAY+"Increase your foraging level by", ChatColor.GRAY+"chopping down trees.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + foragingLevel,
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(foragingLevel+1)-sd.get("foraging").levelExp),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("foraging").totalExp
                ));
        ItemStack excavating = GuiHelper.genSimpleMenuItem("Excavating", Material.DIAMOND_SHOVEL,
                Arrays.asList(ChatColor.GRAY+"Increase your excavating level by digging", ChatColor.GRAY+"dirt and finding artefacts.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + excavatingLevel,
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(excavatingLevel+1)-sd.get("excavating").levelExp),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("excavating").totalExp
                ));
        ItemStack mining = GuiHelper.genSimpleMenuItem("Mining", Material.STONE_PICKAXE,
                Arrays.asList(ChatColor.GRAY+"Increase your mining level by", ChatColor.GRAY+"mining various rocks and minerals.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + miningLevel,
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(miningLevel+1)-sd.get("mining").levelExp),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("mining").totalExp
                ));
        ItemStack mysticism = GuiHelper.genSimpleMenuItem("Mysticism", Material.GLOWSTONE_DUST,
                Arrays.asList(ChatColor.GRAY+"Increase your Mysticism level by", ChatColor.GRAY+"brewing elixirs and enchanting items.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + mysticismLevel,
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(mysticismLevel+1)-sd.get("mysticism").levelExp),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("mysticism").totalExp
                ));
        ItemStack crafting = GuiHelper.genSimpleMenuItem("Crafting", Material.CRAFTING_TABLE,
                Arrays.asList(ChatColor.GRAY+"Increase your crafting level by", ChatColor.GRAY+"crafting and smelting ingredients.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + craftingLevel,
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(craftingLevel+1)-sd.get("crafting").levelExp),
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("crafting").totalExp
                ));
        ItemStack farming = GuiHelper.genSimpleMenuItem("Farming", Material.GOLDEN_HOE,
                Arrays.asList(ChatColor.GRAY+"Increase your farming level by", ChatColor.GRAY+"harvesting and processing plants.",
                        " ", ChatColor.DARK_GRAY+"Your level: " + ChatColor.GREEN + farmingLevel + " LVL",
                        ChatColor.DARK_GRAY + "To next level: " + ChatColor.GREEN + (ints.get(farmingLevel+1)-sd.get("farming").levelExp) + " EXP",
                        ChatColor.DARK_GRAY + "Total experience: " + ChatColor.GREEN + sd.get("farming").totalExp + " EXP"
                ));

        ItemStack gls = GuiHelper.getMenuGlass();

        ItemStack back = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Go back", Material.ARROW, Collections.emptyList());

        ItemStack[] contents = {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, combat, gls, gls, mysticism, gls, gls, excavating, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, mining, gls, gls, gls, gls, gls, crafting, gls,
                gls, gls, gls, farming, gls, foraging, gls, gls, gls,
                back, gls, gls, gls, gls, gls, gls, gls, gls,
        };

        base.setContents(contents);

        return base;
    }

    @Override
    public String getId() {
        return SkyblockD.getNamespace("inventory_skills");
    }
}
