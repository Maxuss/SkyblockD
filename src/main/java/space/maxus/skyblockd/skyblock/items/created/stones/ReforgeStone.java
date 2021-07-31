package space.maxus.skyblockd.skyblock.items.created.stones;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

import java.util.Arrays;

public class ReforgeStone {
    private final ItemStack item;

    public ItemStack getItem() {
        return item;
    }

    public ReforgeStone(String name, @NotNull SkyblockReforge stored, @NotNull SkyblockRarity rarity, @NotNull String hash) {
        ItemStack skull = SkullCreator.itemFromBase64(hash);
        ItemMeta m = skull.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("reforgeStone"), PersistentDataType.INTEGER, stored.getIndex());
        m.setLore(Arrays.asList(
                ChatColor.GRAY+"Use this reforge stone at "+ChatColor.GREEN+"Reforge Anvil",
                ChatColor.GRAY+"to apply "+ChatColor.BLUE+stored.getDisplayName()+ChatColor.GRAY+" reforge to items!",
                " ",
                rarity.displayName+" REFORGE STONE"
        ));
        m.setDisplayName(rarity.displayColor+name);
        m.getPersistentDataContainer().set(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING, "true");
        skull.setItemMeta(m);
        item = skull;
    }
}
