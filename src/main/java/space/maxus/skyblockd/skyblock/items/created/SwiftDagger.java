package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Collections;
import java.util.UUID;

public class SwiftDagger extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_SWORD,
                "Swift Dagger",
                SkyblockRarity.RARE,
                SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(10).setStrength(10).setAttackSpeed(50)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY + "Legacy..."));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("swift_dagger");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 20*0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        blockVanillaRecipes(m);
        addStrength(10, m);
        i.setItemMeta(m);
        return i;
    }
}
