package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.skyblock.items.Shortbow;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;
import java.util.UUID;

public class KasmirFury extends Shortbow {
    @Override
    public Class<? extends Projectile> getProjectileType() {
        return WitherSkull.class;
    }

    @Override
    public String getId() {
        return "KASMIR";
    }

    @Override
    public float getShootCooldown() {
        return 0.9f;
    }

    @Override
    public double getArrowDamage() {
        return 150;
    }

    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.BOW,
                "Kasmir's Fury", SkyblockRarity.RELIC, SkyblockItemType.SHORTBOW,
                new SkyblockItemStats().setDamage(150).setHealth(100));
        cfg.setDescription(Arrays.asList(ChatColor.GRAY + "All arrows shoot from this bow", ChatColor.GRAY + "are turned into "+ChatColor.DARK_PURPLE+"Charged Wither Skulls!"));
        applyAbility(cfg);
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage", 150, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("generic.maxHealth", 20, AttributeModifier.Operation.ADD_NUMBER));
        m.addEnchant(Enchantment.ARROW_DAMAGE, 25, true);
        m.addEnchant(Enchantment.ARROW_KNOCKBACK, 3, true);
        i.setItemMeta(m);
        return i;
    }
}
