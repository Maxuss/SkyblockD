package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.objects.BetterListener;

public class AnvilListener extends BetterListener {
    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent e) {
        AnvilInventory inv = e.getInventory();
        ItemStack first = inv.getFirstItem();
        ItemStack result = e.getResult();
        if(first == null || result == null) return;

        String firstName = first.getI18NDisplayName();
        String secondName = result.getI18NDisplayName();
        if(firstName == null || secondName == null) return;

        if(!ChatColor.stripColor(firstName).equals(ChatColor.stripColor(secondName))) {
            e.setResult(null);
        }
    }
}
