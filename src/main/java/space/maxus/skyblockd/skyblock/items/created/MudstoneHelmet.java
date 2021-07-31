package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Collections;

public class MudstoneHelmet extends SkyblockSkull {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                null, "Mudstone Helmet", SkyblockRarity.RARE,
                SkyblockItemType.HELMET, new SkyblockItemStats().setHealth(25).setSpeed(10)
        );
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility(
                "Mudstone Excavator", SkyblockAbilityType.PASSIVE,
                Collections.singletonList(ChatColor.GOLD+"+5 "+ SkyblockConstants.FORTUNE + " Excavating Fortune"))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("mudstone_helmet");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 2.5d, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", 0.01d, AttributeModifier.Operation.ADD_NUMBER));
        addExcavatingFortune(10, m);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM2ODQxZWNmNjNiMjU0MzE5ZWJhYzk5YzZmMjk1Mzk5Mzc5NTM1Y2U4MzViOTEzNGZjNWZkZmIyMGMwYTEifX19";
    }
}
