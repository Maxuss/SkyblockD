package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class Thanathophobia extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.BOW, "Thanathophobia",
                SkyblockRarity.EPIC, SkyblockItemType.BOW,
                new SkyblockItemStats().setDamage(40).setSpeed(100)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Unholy power!"));
        SkyblockItemAbility abil = new SkyblockItemAbility("Triple shot", SkyblockAbilityType.PASSIVE,
                Collections.singletonList(ChatColor.GRAY+"Fires three arrows at same time!"));
        SkyblockItemAbility a = new SkyblockItemAbility("Fear of Death", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"When at or below " + ChatColor.RED+"10 Health"+ChatColor.GRAY + " deal",
                        ChatColor.GRAY+"double damage to all enemies!"));
        cfg.setAbilities(Arrays.asList(a, abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("thanathophobia");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(),"generic.attackDamage", 40, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.1, AttributeModifier.Operation.ADD_NUMBER));
        m.getPersistentDataContainer().set(SkyblockD.getKey("THANATHOPHOBIA"), PersistentDataType.BYTE, (byte)0);
        m.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
        i.setItemMeta(m);
        return i;
    }
}
