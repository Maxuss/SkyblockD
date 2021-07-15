package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ContainerHelper;
import space.maxus.skyblockd.helpers.UniversalHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.objects.PlayerContainer;
import space.maxus.skyblockd.objects.SkillContainer;
import space.maxus.skyblockd.skyblock.utility.SkillHelper;

import java.util.*;

public class CraftListener extends BetterListener {

    @EventHandler
    public void onPlayerCraft(PrepareItemCraftEvent e) {
        if (e.getView().getPlayer() instanceof Player) {
            Player player = (Player)e.getView().getPlayer();
            CraftingInventory craftingInventory = e.getInventory();
            if (craftingInventory.getType() == InventoryType.WORKBENCH) {
                byte b;
                int i;
                ItemStack[] arrayOfItemStack;
                for (i = (arrayOfItemStack = craftingInventory.getContents()).length, b = 0; b < i; ) {
                    ItemStack stack = arrayOfItemStack[b];
                    if (stack != null && stack.hasItemMeta()) {
                        ItemMeta itemMeta = stack.getItemMeta();
                        assert itemMeta != null;
                        String name = ChatColor.stripColor(itemMeta.getDisplayName());
                        PersistentDataContainer c = itemMeta.getPersistentDataContainer();
                        if (c.has(SkyblockD.getKey("restrictRecipes"), PersistentDataType.STRING)) {
                            player.sendMessage(ChatColor.RED + "You cannot craft with this item.");
                            e.getInventory().setResult(new ItemStack(Material.AIR));
                        }
                    }
                    b++;
                }
            }
        }
    }

    @EventHandler
    public void onCraftFinish(CraftItemEvent e){
        Recipe r = e.getRecipe();
        if(r instanceof ShapedRecipe) {
            ShapedRecipe sr = (ShapedRecipe) r;
            HashMap<Character, ItemStack> map = (HashMap<Character, ItemStack>) sr.getIngredientMap();
            String[] shape = sr.getShape();
            List<ItemStack> iterable = new ArrayList<>();
            for (String sh: shape) {
                char[] ds = sh.toCharArray();
                for (char ch: ds) {
                    if(map.containsKey(ch) && !iterable.contains(map.get(ch))) {
                        iterable.add(map.get(ch));
                    }
                }
            }
            operateItems(e, iterable);
        }
        else if(r instanceof ShapelessRecipe) {
            ShapelessRecipe sr = (ShapelessRecipe) r;
            operateItems(e, sr.getIngredientList());
        }
    }

    private void operateItems(CraftItemEvent e, List<ItemStack> iterable) {
        int totalExp = 0;
        for(ItemStack i : iterable) {
            boolean isSb = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
            boolean wasCrafted = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("craftedPreviously"), PersistentDataType.STRING);
            if (!isSb) CustomItem.toSkyblockItem(i);
            if(!wasCrafted) {
                Integer rar = i.getItemMeta().getPersistentDataContainer().get(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER);
                totalExp += rar == null ? 0 : rar;
                Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer().set(SkyblockD.getKey("craftedPreviously"), PersistentDataType.STRING, "true");
            }
        }
        Player p = (Player) e.getWhoClicked();
        List<PlayerContainer> containers = UniversalHelper.filter(SkyblockD.players, c -> c.uuid.equals(p.getUniqueId()));
        PlayerContainer pc = containers.get(containers.size()-1);

        int lvl = pc.skills.data.get("crafting").currentLevel;
        int tlvl = lvl == 0 ? 1 : lvl;

        float exp = totalExp * SkillHelper.getModifier(tlvl) * 6;

        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        String sxp = String.valueOf(exp).replace(",", ".");
        String rawCommand = "title "+p.getName()+" actionbar {\"text\":\"+"+sxp+" Crafting Experience\", \"color\":\"dark_aqua\"}";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rawCommand);

        pc.skills.totalExp += exp;
        SkillContainer skc = pc.skills.data.get("crafting");
        skc.totalExp += exp;
        skc.levelExp += exp;
        int toNext = SkyblockD.getMapManager().getMaps().get("crafting").getExperience().table
                .get(skc.currentLevel + 1);
        int div = skc.levelExp - toNext;
        if(div >= 0) {
            skc.levelExp = div;
            p.sendMessage(new String[]{
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------",
                            ChatColor.YELLOW + "" + ChatColor.BOLD + "CRAFTING LEVEL UP!",
                            " ",
                            ChatColor.GREEN + "You are now crafting level " + (skc.currentLevel + 1) + "!",
                            ChatColor.GREEN + "Check out new level rewards in Skyblock Menu!",
                            ChatColor.GOLD + "" + ChatColor.BOLD + "-----------------------------"
                    }
            );
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5f);
            int level = skc.currentLevel;
            skc.currentLevel++;
            String levl = "unlocked."+level;
            skc.collectedRewards.put(levl, true);
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
