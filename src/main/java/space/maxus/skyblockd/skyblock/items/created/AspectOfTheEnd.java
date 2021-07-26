package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class AspectOfTheEnd extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.DIAMOND_SWORD, "Aspect of the End", SkyblockRarity.EPIC,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(40).setSpeed(25));
        SkyblockItemAbility abil = new SkyblockItemAbility("Instant Trasmission", SkyblockAbilityType.RIGHT_CLICK,
                Arrays.asList(
                        ChatColor.GRAY+"Instantly teleport 7 blocks",
                        ChatColor.GRAY+"ahead, and gain more speed for",
                        ChatColor.GRAY+"some time."));
        cfg.setAbilities(Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("aspect_of_the_end");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("AOTE"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
