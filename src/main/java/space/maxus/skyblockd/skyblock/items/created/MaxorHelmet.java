package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public class MaxorHelmet extends SkyblockSkull implements WitherPiece {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                null, "Maxor's Helmet",
                SkyblockRarity.RELIC, SkyblockItemType.HELMET,
                new SkyblockItemStats().setDefense(80).setAbilityDamage(15).setHealth(100).setSpeed(40));
        cfg.setAbilities(getAbility());
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("maxor_helmet");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.04d, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10d, AttributeModifier.Operation.ADD_NUMBER));
        addAbilityDamage(15, m);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ1MGE4ZmQ3YTFkZDQ3MWU4ZjkyOTJmMTI4M2U5OWM1ZTUyYTUxODYzMDJiZmYzYTkzZjgwN2ZhOWI0NDJhNCJ9fX0=";
    }
}
