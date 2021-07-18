package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Collections;

public class ShadowFractureChestplate extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_CHESTPLATE, "Shadow Fracture Chestplate",
                SkyblockRarity.LEGENDARY, SkyblockItemType.CHESTPLATE,
                new SkyblockItemStats().setStrength(200).setHealth(100).setSpeed(30).setDefense(100)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Fractured"));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Shadows behind", SkyblockAbilityType.FULL_SET_BONUS,
                        Collections.singletonList(ChatColor.GRAY
                                +"Vastly increases your "
                                +ChatColor.WHITE+"Speed"+ChatColor.GRAY+"!"))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("shadow_chestplate");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 30, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.03, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 10, AttributeModifier.Operation.ADD_NUMBER));
        i.setItemMeta(m);
        return i;
    }
}
