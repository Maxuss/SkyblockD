package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class HydraulicPress extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "Hydraulic Press",
                SkyblockRarity.EPIC, SkyblockItemType.ACCESSORY,
                new SkyblockItemStats()
        );
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Compress!", SkyblockAbilityType.PASSIVE,
                        Arrays.asList(
                                ChatColor.GRAY+"Compresses all items that go to",
                                ChatColor.GRAY+"your inventory into their enchanted",
                                ChatColor.GRAY+"form!"
                        ))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("personal_compactor");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().remove(SkyblockD.getKey("headClick"));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZmZjNhODdlYTJhNjNiNWI3MWM0Mjk3ZGRhN2JmZjJiY2RiNjhiNjJkMWQ1ZTJiZDZlNDNiNTM4MGY5ZWIyOCJ9fX0=";
    }
}
