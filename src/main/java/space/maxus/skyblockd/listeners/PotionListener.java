package space.maxus.skyblockd.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.elixirs.created.*;

import java.util.HashMap;

public class PotionListener extends BetterListener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        ItemMeta m = i.getItemMeta();
        assert m != null;
        PersistentDataContainer c = m.getPersistentDataContainer();
        if(c.has(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING)) {
            String effect = c.get(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING);
            ElixirEffect eff;
            HashMap<String, ElixirEffect> effects = new HashMap<String, ElixirEffect>() {
                {
                    put("insanity", new InsanityEffect(i));
                    put("fury", new FuryEffect(i));
                    put("berserk", new BerserkEffect(i));
                    put("fleet", new FleetEffect(i));
                    put("digger", new DiggerEffect(i));
                    put("paralysis", new ParalysisEffect(i));
                    put("yeti", new YetiEffect(i));
                    put("dragon", new DragonEffect(i));
                }
            };
            if(!effects.containsKey(effect)) eff = new FailedEffect(i);
            else eff = effects.get(effect);
            eff.applyEffect(p);
        }
    }
}
