package space.maxus.skyblockd.events;

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
import space.maxus.skyblockd.skyblock.items.SkyblockItem;

import java.util.Objects;

import static org.bukkit.event.Event.Result;

public class InventoryListener extends BetterListener{

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if(title.contains("Skill")) {
            e.setResult(Result.DENY);
            p.updateInventory();
        }
        if (title.equalsIgnoreCase(ChatColor.DARK_GRAY + "SkyblockD Menu")) {
            e.setResult(Result.DENY);
            p.updateInventory();
        } else if (title.equalsIgnoreCase("Recombobulate an item")) {
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
    }
}
