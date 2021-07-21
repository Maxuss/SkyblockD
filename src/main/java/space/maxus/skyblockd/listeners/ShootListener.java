package space.maxus.skyblockd.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.items.AbilityStorage;

public class ShootListener extends BetterListener {
    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        LivingEntity ent = e.getEntity();
        if(!(ent instanceof Player)) return;
        Player p = (Player) ent;
        ItemStack bow = e.getBow();
        if(bow == null || !bow.hasItemMeta()) return;
        ItemMeta m = bow.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        if(c.has(SkyblockD.getKey("THANATHOPHOBIA"), PersistentDataType.BYTE)) {
            AbilityStorage.tripleShot(p);
        }
    }
}
