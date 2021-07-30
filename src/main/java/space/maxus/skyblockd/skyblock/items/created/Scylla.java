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

public class Scylla extends SkyblockItem implements WitherBlade {

    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_SWORD, "Scylla", SkyblockRarity.RELIC,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(200)
                .setSpeed(50).setAbilityDamage(15).setStrength(75)
        );
        cfg.setDescription(
                Arrays.asList(
                        ChatColor.GRAY+"Deals "+ChatColor.RED+"+50%"+ChatColor.GRAY+" damage to withers.",
                        ChatColor.GRAY+"This item becomes stronger the",
                        ChatColor.GRAY+"higher your "+ChatColor.GREEN+"Foraging"+ChatColor.GRAY+" level is."
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
        return SkyblockD.getNamespace("scylla_blade");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.05, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(),"generic.attackDamage", 200, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.getPersistentDataContainer().set(SkyblockD.getKey("WITHER_BLADE"), PersistentDataType.BYTE, (byte)0);
        m.getPersistentDataContainer().set(SkyblockD.getKey("SCYLLA"), PersistentDataType.BYTE, (byte)0);
        addAbilityDamage(15, m);
        addStrength(75, m);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }
}
