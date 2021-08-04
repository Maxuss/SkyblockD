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

public class YetiSword extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.IRON_SWORD, "Yeti Sword", SkyblockRarity.RELIC,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(150).setStrength(400).setAbilityDamage(25)
        );
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility("Toss",
                SkyblockAbilityType.RIGHT_CLICK, Arrays.asList(
                ChatColor.GRAY+"Toss multiple block in front",
                ChatColor.GRAY+"of you into your foes!"
        ))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("yeti_sword");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 150, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.setUnbreakable(true);
        addAbilityDamage(25, m);
        addStrength(400, m);
        m.getPersistentDataContainer().set(SkyblockD.getKey("YETI_SWORD"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
