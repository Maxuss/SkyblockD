package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.Shortbow;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class EmeraldShortbow extends Shortbow {
    @Override
    public @NotNull Class<? extends Projectile> getProjectileType() {
        return Arrow.class;
    }

    @Override
    public @NotNull String getId() {
        return "EMERALD";
    }

    @Override
    public float getShootCooldown() {
        return 0.4f;
    }

    @Override
    public double getArrowDamage() {
        return 5;
    }

    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.BOW,
                "Emerald Shortbow", SkyblockRarity.RARE, SkyblockItemType.SHORTBOW,
                new SkyblockItemStats().setDamage(5));
        cfg.setDescription(Collections.singletonList(ChatColor.GRAY+"Arrows are very fast!"));
        applyAbility(cfg);
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
