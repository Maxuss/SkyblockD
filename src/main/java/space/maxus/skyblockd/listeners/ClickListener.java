package space.maxus.skyblockd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.gui.ElixirGui;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.events.SkyblockItemClickEvent;

import java.util.Objects;

public class ClickListener extends BetterListener {
    @EventHandler
    public void onClick(@NotNull PlayerInteractEvent e){
        ItemStack i = e.getItem();
        if(i == null) return;
        ItemMeta m = i.getItemMeta();
        assert m != null;
        boolean h = m.getPersistentDataContainer().has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING);
        if(h) {
            SkyblockItemClickEvent ev = new SkyblockItemClickEvent(e);
            SkyblockD.getPluginManager().callEvent(ev);
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && m.getPersistentDataContainer().has(SkyblockD.getKey("itemType"), PersistentDataType.STRING)) {
            Block b = e.getClickedBlock();
            if(b != null && b.getType() == Material.CAULDRON) {
                Location loc = b.getLocation();
                loc.setY(loc.getY()-1);
                Block fire = Objects.requireNonNull(loc.getWorld()).getBlockAt(loc);
                loc.setY(loc.getY()+2);
                Block dia = Objects.requireNonNull(loc.getWorld()).getBlockAt(loc);
                if(fire.getType() == Material.SOUL_FIRE && dia.getType() == Material.DIAMOND_BLOCK) {
                    ItemStack mh = e.getPlayer().getInventory().getItemInMainHand();
                    mh.setAmount(mh.getAmount()-1);
                    ElixirGui g = new ElixirGui();
                    Inventory inv = g.generateContains(Bukkit.createInventory(g.getHolder(e.getPlayer()), g.getSize(), g.getName()));
                    e.getPlayer().openInventory(inv);
                }
            }
        }
    }
}
