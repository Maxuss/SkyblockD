package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class TuxedoChestplate extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_CHESTPLATE, "Tuxedo Chestplate", SkyblockRarity.LEGENDARY,
                SkyblockItemType.CHESTPLATE, new SkyblockItemStats().setStrength(500));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Dashing", SkyblockAbilityType.PASSIVE, Arrays.asList(
                        ChatColor.GRAY + "Deal lots of damage by the",
                        ChatColor.GRAY + "cost of your health!"))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("tuxedo_chestplate");
    }

    @Override
    public @Nullable ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        addStrength(500, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, -20, m);
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(26, 26, 26));
        i.setItemMeta(lm);
        return i;
    }
}
