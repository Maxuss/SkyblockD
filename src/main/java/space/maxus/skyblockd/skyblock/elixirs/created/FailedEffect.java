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

import java.util.Arrays;

public class FailedEffect extends ElixirEffect {
    public FailedEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(Player p) {
        PotionEffect e = new PotionEffect(PotionEffectType.CONFUSION, 100, 2);
        p.addPotionEffects(Arrays.asList(e));
    }

    @Override
    public String getEffectName() {
        return ChatColor.GRAY+"None! Elixir failed :(";
    }

    @Override
    public ItemStack addTag(ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "fail");
        item.setItemMeta(m);
        return item;
    }
}
