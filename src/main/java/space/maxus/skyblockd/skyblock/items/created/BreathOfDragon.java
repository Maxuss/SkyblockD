package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class BreathOfDragon extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.NETHERITE_SWORD, "Breath of Erumdir" , SkyblockRarity.MYTHIC,
                SkyblockItemType.SWORD, new SkyblockItemStats()
                .setHealth(50).setSpeed(50).setDamage(100).setDefense(100));
        cfg.setAbilities(Arrays.asList(new SkyblockItemAbility("Dragon Affection", SkyblockAbilityType.PASSIVE,
                Collections.singletonList(ChatColor.GRAY+"Deal double damage to dragons!")),
                new SkyblockItemAbility("Erumdir's Curse", SkyblockAbilityType.RIGHT_CLICK,
                        Arrays.asList(ChatColor.GRAY+"Implode, dealing insane",
                                ChatColor.GRAY+"damage to enemies around you.",
                                ChatColor.GRAY+"After the implosion, a soul fire aura will",
                                ChatColor.GRAY+"set all enemies on fire!"))));
        cfg.setDescription(Arrays.asList(ChatColor.DARK_GRAY+"'I will come back'",ChatColor.DARK_GRAY+"  - Erumdir"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("aspect_of_dragon");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 5, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.05, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 100, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.getPersistentDataContainer().set(SkyblockD.getKey("ASPECT_OF_DRAGON"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
