package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.items.Shortbow;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class DraconicShortbow extends Shortbow {
    @Override
    public @NotNull Class<? extends Projectile> getProjectileType() {
        return Fireball.class;
    }

    @Override
    public @NotNull String getId() {
        return "DRAGON";
    }

    @Override
    public float getShootCooldown() {
        return 1.4f;
    }

    @Override
    public double getArrowDamage() {
        return 75;
    }

    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.BOW,
                "Draconic Shortbow", SkyblockRarity.EPIC, SkyblockItemType.SHORTBOW,
                new SkyblockItemStats().setDamage(50).setSpeed(-50));
        cfg.setDescription(Arrays.asList(ChatColor.GRAY + "All arrows shoot from this bow", ChatColor.GRAY + "are turned into powerful fireballs!"));
        applyAbility(cfg);
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", 75, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier("generic.movementSpeed", -0.05, AttributeModifier.Operation.ADD_NUMBER));
        i.setItemMeta(m);
        return i;
    }
}
