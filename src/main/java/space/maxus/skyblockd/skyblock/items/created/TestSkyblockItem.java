package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Collections;

public class TestSkyblockItem extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.DIAMOND_SWORD, "Aspect of the Maxus",
                SkyblockRarity.VERY_SPECIAL, SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(50).setCritDamage(70).setSoulflow(120).setStrength(100)
                );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY + "Exclusive admin item!"));
        SkyblockItemAbility abil = new SkyblockItemAbility("Ban", SkyblockAbilityType.DOUBLE_RIGHT_CLICK,
                Collections.singletonList(ChatColor.GRAY + "Bans any user you look at!"));
        abil.setCooldown(10);
        abil.setManaCost(25);
        abil.setSoulflowCost(50);
        cfg.setAbilities(Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("sb_item_test");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
