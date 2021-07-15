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

public class ParalysisEffect extends ElixirEffect {
    public ParalysisEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(Player p) {
        PotionEffect e1 = new PotionEffect(PotionEffectType.BLINDNESS, 400, 1);
        PotionEffect e2 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5);
        PotionEffect e3 = new PotionEffect(PotionEffectType.SLOW, 400, 6);

        p.addPotionEffects(Arrays.asList(e1, e2, e3));
    }

    @Override
    public String getEffectName() {
        return ChatColor.DARK_GRAY+"Paralysis " + SkyblockConstants.TRUE_DEFENCE;
    }

    @Override
    public ItemStack addTag(ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "paralysis");
        item.setItemMeta(m);
        return item;
    }
}
