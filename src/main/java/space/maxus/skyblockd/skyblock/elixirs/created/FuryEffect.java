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

import java.util.Arrays;

public class FuryEffect extends ElixirEffect {
    public FuryEffect(ItemStack item) {
        super(item);
    }

    @Override
    public void applyEffect(@NotNull Player p) {
        PotionEffect e1 = new PotionEffect(PotionEffectType.SPEED, 400, 1);
        PotionEffect e2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 3);
        p.addPotionEffects(Arrays.asList(e1, e2));
    }

    @Override
    public @NotNull String getEffectName() {
        return ChatColor.GOLD + "Fury ෴";
    }

    @Override
    public @NotNull ItemStack addTag(@NotNull ItemStack item) {
        ItemMeta m = item.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING, "fury");
        item.setItemMeta(m);
        return item;
    }
}
