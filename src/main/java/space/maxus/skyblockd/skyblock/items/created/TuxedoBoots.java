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

public class TuxedoBoots extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_BOOTS, "Tuxedo Boots", SkyblockRarity.LEGENDARY,
                SkyblockItemType.BOOTS, new SkyblockItemStats().setStrength(400));
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
        return SkyblockD.getNamespace("tuxedo_boots");
    }

    @Override
    public @Nullable ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        addStrength(400, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, -10, m);
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(33, 33, 33));
        i.setItemMeta(lm);
        return i;
    }
}
