package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class Thanathos extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_SWORD, "Thanathos",
                SkyblockRarity.LEGENDARY, SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(60).setSpeed(50)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Unleash blazes!"));
        SkyblockItemAbility abil = new SkyblockItemAbility("God of Death", SkyblockAbilityType.RIGHT_CLICK,
                Arrays.asList(ChatColor.GRAY+"Teleport "+ChatColor.GOLD+"5 blocks"+ChatColor.GRAY+" ahead, and cause",
                        ChatColor.GRAY+"massive explosion, dealing huge damage to",
                        ChatColor.GRAY+"mobs around you."));
        abil.setCooldown(2);
        cfg.setAbilities(Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("thanathos");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(),"generic.attackDamage", 60, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.05, AttributeModifier.Operation.ADD_NUMBER));
        m.getPersistentDataContainer().set(SkyblockD.getKey("THANATHOS"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
