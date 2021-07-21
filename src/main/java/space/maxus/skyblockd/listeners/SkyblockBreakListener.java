package space.maxus.skyblockd.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.items.AbilityStorage;

public class SkyblockBreakListener extends BetterListener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if(item.getType() != Material.AIR) {
            ItemMeta m = item.getItemMeta();
            assert m != null;
            PersistentDataContainer c = m.getPersistentDataContainer();
            if(c.has(SkyblockD.getKey("ETHEREAL_CRUSHER"), PersistentDataType.BYTE)) {
                AbilityStorage.stoneBlockBreaker(e.getBlock(), AbilityStorage.ETHEREAL_CRUSHER_AMOUNT, item, AbilityStorage.ETHEREAL_CRUSHER_CD, p);
            } else if(c.has(SkyblockD.getKey("JUNGLE_AXE"), PersistentDataType.BYTE)) {
                AbilityStorage.woodenBlockBreaker(e.getBlock(), AbilityStorage.JUNGLE_AXE_AMOUNT, item, AbilityStorage.JUNGLE_AXE_CD, p);
            } else if(c.has(SkyblockD.getKey("TREECAPITATOR"), PersistentDataType.BYTE)) {
                AbilityStorage.woodenBlockBreaker(e.getBlock(), AbilityStorage.TREECAPITATOR_AMOUNT, item, AbilityStorage.TREECAPITATOR_CD, p);
            } else if(c.has(SkyblockD.getKey("TREENIHILATOR"), PersistentDataType.BYTE)) {
                AbilityStorage.woodenBlockBreaker(e.getBlock(), AbilityStorage.TREENIHILATOR_AMOUNT, item, AbilityStorage.TREENIHILATOR_CD, p);
            } else if(c.has(SkyblockD.getKey("WORLD_DIGESTER"), PersistentDataType.BYTE)) {
                AbilityStorage.dirtBlockBreaker(e.getBlock(), AbilityStorage.WORLD_DIGESTER_AMOUNT, item, AbilityStorage.WORLD_DIGESTER_CD, p);
            }
        }
    }
}
