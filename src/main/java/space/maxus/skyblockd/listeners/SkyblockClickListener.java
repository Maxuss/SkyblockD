package space.maxus.skyblockd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.events.SkyblockItemClickEvent;
import space.maxus.skyblockd.skyblock.items.AbilityStorage;

import java.util.HashMap;
import java.util.Objects;

public class SkyblockClickListener extends BetterListener {
    private final static HashMap<String, Long> lastCommands = new HashMap<>();
    private final static long cd = 2 * 1000L;

    private final static PersistentDataType<Byte, Byte> b = PersistentDataType.BYTE;

    @EventHandler
    public void onClick(SkyblockItemClickEvent ev) {
        PlayerInteractEvent e = ev.getPredcessor();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if(i == null) return;

            PersistentDataContainer c = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
            if (c.has(SkyblockD.getKey("ETHEREAL_CRUSHER"), b)) {
                AbilityStorage.crusherAbility(i, p);
            } else if (c.has(SkyblockD.getKey("ASPECT_OF_DRAGON"), b)) {
                AbilityStorage.dragonAspectAbility(i, p);
            } else if (c.has(SkyblockD.getKey("THANATHOS"), b)) {
                AbilityStorage.thanathosAbility(i, p);
            }
        }
    }
}
