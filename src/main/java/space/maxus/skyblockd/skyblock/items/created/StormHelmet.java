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

public class StormHelmet extends SkyblockSkull implements WitherPiece {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                null, "Storm's Helmet",
                SkyblockRarity.RELIC, SkyblockItemType.HELMET,
                new SkyblockItemStats().setDefense(80).setAbilityDamage(25).setHealth(100));
        cfg.setAbilities(getAbility());
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("storm_helmet");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10d, AttributeModifier.Operation.ADD_NUMBER));
        addAbilityDamage(25, m);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2MzM2IxZTk2YjhiYTA3OGE5MWYwMDAyZDQ5MTljNjE1NTQzY2QwOTNjMmQ3MDlkOWFlZTlmNjI2ODEzNGMyYyJ9fX0=";
    }
}
