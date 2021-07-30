package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;
import java.util.UUID;

public class EndstoneSword extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GOLDEN_SWORD,
                "Endstone Sword",
                SkyblockRarity.EPIC,
                SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(50).setAttackSpeed(25).setHealth(10)
                );
        cfg.setDescription(Arrays.asList("", ChatColor.DARK_GRAY + "Power of endstone in your hands."));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("endstone_sword");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 20*0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        blockVanillaRecipes(m);
        i.setItemMeta(m);
        i.addEnchantment(Enchantment.DURABILITY, 3);
        return i;
    }
}
