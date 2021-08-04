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

public class GiantSword extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_SWORD, "Giant's Sword", SkyblockRarity.LEGENDARY,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(300).setAbilityDamage(25)
        );
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility("Giant's Slam",
                SkyblockAbilityType.RIGHT_CLICK, Arrays.asList(
                        ChatColor.GRAY+"Slam your sword into the ground,",
                ChatColor.GRAY+"dealing tons of damage to",
                ChatColor.GRAY+"enemies to around you!"
        ))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("giant_sword");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 300, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.setUnbreakable(true);
        addAbilityDamage(25, m);
        m.getPersistentDataContainer().set(SkyblockD.getKey("GIANT_SWORD"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
