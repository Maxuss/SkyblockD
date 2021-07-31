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

import java.util.Arrays;
import java.util.Collections;

public class NautilusHelmet extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                null, "Nautilus Helmet", SkyblockRarity.EPIC,
                SkyblockItemType.BOOTS, new SkyblockItemStats().setSeaCreatureChance(3)
                .setDefense(60).setHealth(10));
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility(
                "Nautilus Shells", SkyblockAbilityType.FULL_SET_BONUS,
                Arrays.asList(
                        ChatColor.GRAY+"Take "+ChatColor.GREEN+"20%"+ChatColor.GRAY+" less damage from",
                        ChatColor.GRAY+"from Sea Creatures!"
                )
        )));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("nautilus_helmet");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 1, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 6, AttributeModifier.Operation.ADD_NUMBER));
        addSeaCreatureChance(3, m);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODEyNmNhYzE2ZmQ4ZTQ3NTE2ZTg0NTIwY2QzOTgxYzQ1ZDcwOGY1NWQzNDU4NDk0ZDhmMDgxYzUwNWQ2ZDMwNCJ9fX0=";
    }
}
