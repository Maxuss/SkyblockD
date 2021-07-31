package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Collections;

public class IcarusWings extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.ELYTRA, "Icarus' Wings" ,SkyblockRarity.LEGENDARY,
                SkyblockItemType.CHESTPLATE, new SkyblockItemStats().setHealth(-100).setSpeed(50));
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility("Icarus' Folly", SkyblockAbilityType.PASSIVE,
                Collections.singletonList(ChatColor.GRAY+"Increases your flight speed by a lot!"))));
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"To the sun!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("daedalus_wings");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", -10, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.05, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_FLYING_SPEED,
                new AttributeModifier("generic.flyingSpeed", 0.1, AttributeModifier.Operation.ADD_NUMBER));
        i.setItemMeta(m);
        return i;
    }
}
