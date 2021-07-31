package space.maxus.skyblockd.skyblock.items.created;

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

public class TestShortbow extends Shortbow {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        return new SkyblockItemConfig(Material.BOW, "Test Shortbow", SkyblockRarity.EPIC,
                SkyblockItemType.SHORTBOW, new SkyblockItemStats().setDamage(50));
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Projectile> getProjectileType() {
        return Arrow.class;
    }

    @Override
    public @NotNull String getId() {
        return "TEST";
    }

    @Override
    public float getShootCooldown() {
        return 0;
    }

    @Override
    public double getArrowDamage() {
        return 0;
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        return i;
    }
}
