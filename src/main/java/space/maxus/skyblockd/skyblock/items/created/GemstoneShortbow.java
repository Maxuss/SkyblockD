package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.skyblock.items.Shortbow;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;

public class GemstoneShortbow extends Shortbow {
    @Override
    public Class<? extends Projectile> getProjectileType() {
        return Arrow.class;
    }

    @Override
    public String getId() {
        return "GEMSTONE";
    }

    @Override
    public float getShootCooldown() {
        return 3f;
    }

    @Override
    public double getArrowDamage() {
        return 15;
    }

    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.BOW,
                "Gemstone Shortbow", SkyblockRarity.RARE, SkyblockItemType.SHORTBOW,
                new SkyblockItemStats().setDamage(15));
        cfg.setDescription(Collections.singletonList(ChatColor.GRAY+"Arrows have random effect!"));
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