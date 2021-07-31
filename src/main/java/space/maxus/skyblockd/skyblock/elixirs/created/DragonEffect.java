package space.maxus.skyblockd.skyblock.elixirs.created;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;

public class DragonEffect extends ElixirEffect {
    public DragonEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(@NotNull Player p) {
        PotionEffect e1 = new PotionEffect(PotionEffectType.GLOWING, 800, 2);
        PotionEffect e2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 800, 2);
        PotionEffect e3 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 800, 2);
        PotionEffect e4 = new PotionEffect(PotionEffectType.FAST_DIGGING, 800, 2);
        PotionEffect e5 = new PotionEffect(PotionEffectType.HEALTH_BOOST, 800, 2);
        PotionEffect e6 = new PotionEffect(PotionEffectType.NIGHT_VISION, 800, 1);

        p.addPotionEffects(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }

    @Override
    public @NotNull String getEffectName() {
        return ChatColor.LIGHT_PURPLE + ""
                + ChatColor.MAGIC + "a" + ChatColor.RESET +
                "" + ChatColor.LIGHT_PURPLE+" Dragon " + SkyblockConstants.CRIT_DAMAGE + " "
                + ChatColor.MAGIC + "a";
    }

    @Override
    public @NotNull ItemStack addTag(@NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "dragon");
        item.setItemMeta(m);
        return item;
    }
}
