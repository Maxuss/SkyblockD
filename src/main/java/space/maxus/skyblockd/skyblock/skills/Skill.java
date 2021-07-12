package space.maxus.skyblockd.skyblock.skills;

import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.JsonHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Skill implements SkyblockFeature {

    protected String name;
    protected String prof;
    protected SkillTable levelTable;
    protected StatTable rewardTable;
    protected Player owner;


    // utility constructor for classes extending it
    @Utility
    protected Skill() {
    }

    public abstract String getSkyblockId();

    public abstract Class<? extends SkillResource> getResource();
    public abstract Material getSkillItem();
    public abstract String getSkillResourceFile();
    public abstract Player getOwner(Player p);

    public Skill(Player p) throws IOException {
        String res = getSkillResourceFile();
        JsonHelper<? extends SkillResource> json = new JsonHelper<>(getResource(), true);
        Type t = new TypeToken<SkillResource>(){}.getType();
        SkillResource resource = json.deserializeJson(JsonHelper.readJsonResource(res), t);
        name = resource.getSkillName();
        prof = resource.getProfession();
        levelTable = resource.getLevelTable();
        rewardTable = resource.getStatRewards();
        owner = getOwner(p);
    }

    public Inventory generateMenu() {
        Inventory i = Bukkit.createInventory(owner, 54, name + " Skill");
        ItemStack gls = GuiHelper.getMenuGlass();
        List<Integer> levels = levelTable.table;
        List<SimpleReward> stats = rewardTable.statValues;
        // assigning null to air so it looks better
        ItemStack air = new ItemStack(Material.AIR);
        i.setContents(new ItemStack[] {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, gls, gls, gls, gls, gls, gls, gls, gls
        });
        for (int j = 0; j < levels.size(); j++) {
            int xp = levels.get(j);
            SimpleReward rew = stats.get(j);
            List<String> lore = new ArrayList<>();
            int sa = xp <= 0 ? 1 : xp;
            lore.add(ChatColor.DARK_GRAY + prof + " " + ChatColor.DARK_AQUA + (j+1));
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Requires " + ChatColor.GREEN + sa + " " + name + ChatColor.GRAY + " Experience");
            if (rew.getStatName() != null) {
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Grants +" + rew.getStatValue() +" "+ rew.getStatName().replace("&", "§"));
                if (rew.getItemName() != null) {
                    String nn = CustomItem
                            .capitalize(rew.getItemName().toLowerCase(Locale.ENGLISH).replace("_", " "));
                    String na = rew.getItemValue() > 1 ? ChatColor.AQUA + "" + rew.getItemValue() + " " : "a ";
                    lore.add(ChatColor.GRAY + "and " + na + nn.replace("&", "§"));
                }
            }
            ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
            ItemMeta m = item.getItemMeta();
            assert m != null;
            m.setDisplayName(ChatColor.AQUA + name + " " + (j+1));
            m.setLore(lore);
            m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
            GuiHelper.setHideAllFlags(m);
            item.setItemMeta(m);
            i.addItem(item);
        }
        return i;
    }
}
