package space.maxus.skyblockd.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.objects.BetterListener;

public class AnvilListener extends BetterListener {
    @EventHandler
    public void onAnvilUse(final PrepareAnvilEvent e) {
        ItemStack original = e.getInventory().getItem(0);
        if (original == null
                || !original.hasItemMeta()
                || e.getResult() == null) return;
        String displayName = original.getItemMeta().getDisplayName();
        if (!displayName.equals(ChatColor.stripColor(displayName))) {
            final ItemMeta resultMeta = e.getResult().getItemMeta();
            resultMeta.setDisplayName(displayName);
            e.getResult().setItemMeta(resultMeta);
        }
    }
}
