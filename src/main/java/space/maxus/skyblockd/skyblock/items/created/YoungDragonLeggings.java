package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class YoungDragonLeggings extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_LEGGINGS, "Young Dragon Leggings",
                SkyblockRarity.LEGENDARY, SkyblockItemType.LEGGINGS,
                new SkyblockItemStats().setDefense(40).setStrength(50).setSpeed(100));
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility("Young blood", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"Move like the fastest of dragons!",
                        ChatColor.GRAY+"Increases your speed cap."))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("young_leggings");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 4, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 5, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.1, AttributeModifier.Operation.ADD_NUMBER));
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(227, 255, 222));
        i.setItemMeta(lm);
        return i;
    }
}
