package space.maxus.skyblockd.skyblock.skills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.util.Roman;

import java.util.*;

public abstract class ExtendableSkill extends MappedSkill implements ModificableSkill {

    public abstract String getSkyblockId();
    public abstract Material getSkillItem();
    public abstract Player getOwner(Player p);
    public abstract SkillMap getMap();

    @Override
    public Inventory generateMenu(){
        Inventory i = Bukkit.createInventory(owner, 54, name + " Skill");
        ItemStack gls = GuiHelper.getMenuGlass();
        List<Integer> levels = levelTable.table;
        @SuppressWarnings("unchecked")
        List<ComplexReward> stats = (List<ComplexReward>) rewardTable.statValues;

        // assigning null to air so it looks better
        ItemStack air = new ItemStack(Material.AIR);

        ItemStack back = GuiHelper.genSimpleMenuItem(ChatColor.RED+"Go back", Material.ARROW,
                Collections.emptyList());

        i.setContents(new ItemStack[] {
                gls, gls, gls, gls, gls, gls, gls, gls, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, air, air, air, air, air, air, air, gls,
                gls, gls, gls, gls, back, gls, gls, gls, gls
        });
        List<Integer> bytes = Arrays.asList(0, 7, 14, 21);
        for (int j = 0; j < levels.size(); j++) {
            int xp = levels.get(j);
            ComplexReward rew = stats.get(j);
            List<String> lore = new ArrayList<>();
            int sa = xp <= 0 ? 1 : xp;
            lore.add(ChatColor.DARK_GRAY + prof + " " + ChatColor.DARK_AQUA + new Roman((j+1)));
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Requires " + ChatColor.GREEN + sa + " " + name + ChatColor.GRAY + " Experience");
            if (rew.getStatName() != null) {
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Grants +" + rew.getStatValue() +" "+ rew.getStatName().replace("&", "§"));
                if (rew.getItem() != null) {
                    String dn = Objects.requireNonNull(rew.getItem().getItemMeta()).getDisplayName();
                    String nn = dn.equals("") ? CustomItem.capitalize(rew.getItem().getType().name().toLowerCase(Locale.ENGLISH).replace("_", " ")) : dn;
                    String na = rew.getItemValue() > 1 ? ChatColor.AQUA + "" + rew.getItemValue() + " " : "a " + ChatColor.AQUA;
                    lore.add(ChatColor.GRAY + "and " + na + nn.replace("&", "§"));
                }
            }
            ItemStack item = bytes.contains(j) ? new ItemStack(getSkillItem()) :
                    rew.getItem() != null ? new ItemStack(rew.getItem().getType(), 1) :
                    new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
            ItemMeta m = item.getItemMeta();
            assert m != null;
            m.setDisplayName(ChatColor.AQUA + name + " " + new Roman((j+1)));
            m.setLore(lore);
            m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
            GuiHelper.setHideAllFlags(m);
            item.setItemMeta(m);
            i.addItem(item);
        }
        return i;
    }

    public void claimReward(int reward, Player p){
        ContainerHelper.updatePlayers();
        List<PlayerContainer> pl = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
        PlayerContainer cont = pl.get(pl.size()-1);
        String skillname = getSkyblockId().replace("skyblockd:SKILL_", "").toLowerCase(Locale.ENGLISH);
        SkillContainer sc = cont.skills.data.get(skillname);
        HashMap<String, Boolean> da = sc.collectedRewards;
        boolean wasCollected = da.get("collected."+reward);
        boolean wasUnlocked = da.get("unlocked."+reward);

        if(wasCollected) {
            p.sendMessage(ChatColor.RED+"This reward was already claimed!");
            return;
        }
        if(wasUnlocked) {
            da.put("collected."+reward, true);
            ItemStack item = ((ComplexReward) getMap().getRewardList().get(reward)).item;
            p.getInventory().addItem(item == null ? new ItemStack(Material.AIR) : item);
            p.sendMessage(ChatColor.GREEN + "Successfully claimed reward for level " + (reward + 1));
            ContainerHelper.updatePlayers();
        } else {
            p.sendMessage(ChatColor.RED+"You have not yet achieved this level!");
        }
    }

    public ExtendableSkill(Player p) { super(p); }

}
