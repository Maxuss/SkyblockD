package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class Astraea extends SkyblockItem implements WitherBlade {

    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_SWORD, "Astraea", SkyblockRarity.RELIC,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(100)
                .setStrength(350).setDefense(250).setTrueDefense(10)
        );
        cfg.setDescription(
                Arrays.asList(
                        ChatColor.GRAY+"Deals "+ChatColor.RED+"+50%"+ChatColor.GRAY+" damage to withers.",
                        ChatColor.GRAY+"This item becomes stronger the",
                        ChatColor.GRAY+"higher your "+ChatColor.GOLD+"Mining"+ChatColor.GRAY+" level is."
                ));
        cfg.setAbilities(Collections.singletonList(getAbility()));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("astraea_blade");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR_TOUGHNESS,
                new AttributeModifier("generic.armorToughness", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 25, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 100, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.getPersistentDataContainer().set(SkyblockD.getKey("WITHER_BLADE"), PersistentDataType.BYTE, (byte)0);
        m.getPersistentDataContainer().set(SkyblockD.getKey("ASTRAEA"), PersistentDataType.BYTE, (byte)0);
        addStrength(350, m);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }
}
