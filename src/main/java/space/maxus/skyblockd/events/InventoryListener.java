package space.maxus.skyblockd.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.gui.MainMenuGUI;
import space.maxus.skyblockd.gui.SkillsGui;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.skills.created.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static org.bukkit.event.Event.Result;

public class InventoryListener extends BetterListener{

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if (title.equalsIgnoreCase(ChatColor.DARK_GRAY + "SkyblockD Menu")) {
            menu(e, p);
        } else if (title.equalsIgnoreCase("Recombobulate an item")) {
            recombobulator(e, p);
        } else if (title.equalsIgnoreCase("Your Skills")) {
            skills(e, p);
        } else if (title.contains("Skill")) {
            skillClaim(e, p);
        }
    }

    private void skills(InventoryClickEvent e, Player p){
        if(e.getCurrentItem() == null) return;
        e.setCancelled(true);
        switch(ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName())) {
            // added skills
            case "Combat":
                p.closeInventory();
                p.openInventory(new Combat(p).generateMenu());
                break;
            case "Foraging":
                p.closeInventory();
                p.openInventory(new Foraging(p).generateMenu());
                break;
            case "Excavating":
                p.closeInventory();
                p.openInventory(new Excavating(p).generateMenu());
                break;
            case "Mining":
                p.closeInventory();
                p.openInventory(new Mining(p).generateMenu());
                break;
            case "Mysticism":
                p.closeInventory();
                p.openInventory(new Mysticism(p).generateMenu());
                break;
            case "Crafting":
                p.closeInventory();
                p.openInventory(new Crafting(p).generateMenu());
                break;
            case "Farming":
                p.closeInventory();
                p.openInventory(new Farming(p).generateMenu());
                break;
            case "Go back":
                MainMenuGUI mmg = new MainMenuGUI();
                mmg.setPlayer(p);
                p.closeInventory();
                p.openInventory(mmg.generateContains(Bukkit.createInventory(mmg.getHolder(p), mmg.getSize(), mmg.getName())));
                break;
        }
    }

    private void menu(InventoryClickEvent e, Player p) {
        if(e.getCurrentItem() == null) return;
        if(Objects.requireNonNull(e.getCurrentItem().getItemMeta())
                .getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"Your Skills")) {
            SkillsGui ui = new SkillsGui();
            ui.setPlayer(p);
            Inventory inv = Bukkit.createInventory(ui.getHolder(p), ui.getSize(), ui.getName());
            p.openInventory(ui.generateContains(inv));
        }
        e.setResult(Result.DENY);
        p.updateInventory();
    }

    private void recombobulator(InventoryClickEvent e, Player p) {
        ItemStack i = e.getCurrentItem();
        if (i != null) {
            PersistentDataContainer c = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
            String name = ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName());
            Inventory inv = e.getInventory();
            if (c.has(SkyblockD.getKey("recombobulated"), PersistentDataType.STRING)) {
                p.sendMessage(ChatColor.RED+"You can not recombobulate an item twice!");
            } else if(!c.has(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER)){
                p.sendMessage(ChatColor.RED+"This item can not be recombobulated!");
            } else if(i.getAmount() > 1){
                p.sendMessage(ChatColor.RED+"You can only recombobulate one item at a time!");
            } else if(
                    !name.contains("Recombobulator")
                            && i.getType() != Material.GRAY_STAINED_GLASS_PANE
                            && i.getType() != Material.ANVIL) {
                if(inv.getItem(13) != null){
                    p.getInventory().addItem(inv.getItem(13));
                }
                inv.setItem(13, i);
                p.getInventory().remove(i);
            }

            if (name.equalsIgnoreCase("Recombobulate!")){
                ItemStack lable = inv.getItem(13);
                if(lable == null){
                    p.sendMessage(ChatColor.RED+"Please choose an item to recombobulate!");
                } else{
                    ItemStack recombed = SkyblockItem.recombobulate(lable);
                    p.getInventory().addItem(recombed);
                    p.closeInventory();
                    p.sendMessage(ChatColor.GOLD+"Successfully upgraded rarity of " + Objects.requireNonNull(lable.getItemMeta()).getDisplayName()+ChatColor.GOLD+"!");
                    return;
                }
            }
            e.setResult(Result.DENY);
            p.updateInventory();
        }
    }

    private void skillClaim(InventoryClickEvent e, Player p) {
        e.setResult(Result.DENY);
        p.updateInventory();

        String skill = ChatColor.stripColor(e.getView().getTitle().replace(" Skill", "")).toLowerCase(Locale.ENGLISH);
        int index = e.getSlot();

        List<Integer> mgl = Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17, 18, 26, 27, 35, 36,
                44, 45, 46, 47, 48, 49
        );

        if(mgl.contains(index)) return;

        // -10
        List<Integer> row1 = Arrays.asList(
                10, 11, 12, 13, 14, 15, 16
        );
        // -12
        List<Integer> row2 = Arrays.asList(
                19, 20, 21, 22, 23, 24, 25
        );
        // -14
        List<Integer> row3 = Arrays.asList(
                28, 29, 30, 31, 32, 33, 34
        );
        // -16
        List<Integer> row4 = Arrays.asList(
                37, 38, 39, 40, 41, 42, 43
        );

        if(row1.contains(index)) index -= 10;
        else if(row2.contains(index)) index -= 12;
        else if(row3.contains(index)) index -= 14;
        else if(row4.contains(index)) index -= 16;

        switch(skill) {
            case "mining":
                new Mining(p).claimReward(index, p);
                break;
            case "foraging":
                new Foraging(p).claimReward(index, p);
                break;
            case "excavating":
                new Excavating(p).claimReward(index, p);
                break;
        }
    }
}
