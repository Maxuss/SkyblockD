package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class ContaminatedWither extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "Contaminated Wither",
                SkyblockRarity.EPIC, SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats()
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"You can hear screams from it"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("jar_wither");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }

    @Override
    public String getSkinHash() {
        // https://minecraft-heads.com/custom-heads/decoration/33342-ink
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTAyOGQ0NzRjNGEzMzJkY2Y4ZTM0Mzk1N2NlMGNhNGVlYmFjZTI1OGMzN2Y2ZDkwNGNmMjI4ZDM0NTY3ZjU1MSJ9fX0=";
    }
}
