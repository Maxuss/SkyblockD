package space.maxus.skyblockd.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;

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
                        SkyblockD.logger.info(name);
                        PersistentDataContainer c = itemMeta.getPersistentDataContainer();
                        if (c.has(SkyblockD.getKey("restrictRecipes"), PersistentDataType.STRING)) {
                            SkyblockD.logger.info("here");
                            player.sendMessage(ChatColor.RED + "You cannot craft with this item.");
                            e.getInventory().setResult(new ItemStack(Material.AIR));
                        }
                    }
                    b++;
                }
            }
        }
    }
}
