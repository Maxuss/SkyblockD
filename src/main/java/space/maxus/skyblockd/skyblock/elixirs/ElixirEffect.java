package space.maxus.skyblockd.skyblock.elixirs;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public abstract class ElixirEffect {
    public abstract void applyEffect(Player p);

    public abstract @NotNull String getEffectName();

    public abstract @NotNull ItemStack addTag(ItemStack item);

    protected ItemStack i;

    public ItemStack getItem() { return i; }

    public ElixirEffect(@NotNull ItemStack item) {
        addTag(item);

        ItemMeta m = item.getItemMeta();

        assert m != null;
        m.setLore(Arrays.asList(
                " ",
                ChatColor.BLUE + "Effect: " + getEffectName(),
                " ",
                ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "EPIC ELIXIR"
        ));
        m.setDisplayName(ChatColor.DARK_PURPLE+"Brewed Elixir");

        NamespacedKey key = SkyblockD.getKey("skyblockNative");
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");
        m.getPersistentDataContainer().set(SkyblockD.getKey("itemRarity"), PersistentDataType.INTEGER, SkyblockRarity.EPIC.getIndex());
        item.setItemMeta(m);
        i = item;
    }
}
