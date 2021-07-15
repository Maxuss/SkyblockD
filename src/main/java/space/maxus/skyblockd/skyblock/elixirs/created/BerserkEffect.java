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

public class BerserkEffect extends ElixirEffect {
    public BerserkEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(Player p) {
        PotionEffect e1 = new PotionEffect(PotionEffectType.ABSORPTION, 600, 2);
        PotionEffect e2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 3);
        PotionEffect e3 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 1);
        p.addPotionEffects(Arrays.asList(e1, e2, e3));
    }

    @Override
    public String getEffectName() {
        return ChatColor.RED+"Berserk " + SkyblockConstants.FEROCITY;
    }

    @Override
    public ItemStack addTag(ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "berserk");
        item.setItemMeta(m);
        return item;
    }
}
