package space.maxus.skyblockd.skyblock.elixirs.created;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;

public class FleetEffect extends ElixirEffect {
    public FleetEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(Player p) {
        PotionEffect e1 = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 800, 2);
        PotionEffect e2 = new PotionEffect(PotionEffectType.SPEED, 800, 2);
        PotionEffect e3 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1);
        p.addPotionEffects(Arrays.asList(e1, e2, e3));
    }

    @Override
    public String getEffectName() {
        return ChatColor.WHITE+"Fleet "+ SkyblockConstants.SPEED;
    }

    @Override
    public ItemStack addTag(ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "fleet");
        item.setItemMeta(m);
        return item;
    }
}
