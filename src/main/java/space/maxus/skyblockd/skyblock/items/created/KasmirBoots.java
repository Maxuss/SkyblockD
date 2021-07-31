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
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;

public class KasmirBoots extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_BOOTS, "Kasmir Boots",
                SkyblockRarity.RELIC, SkyblockItemType.BOOTS,
                new SkyblockItemStats().setStrength(250).setHealth(-50)
        );
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Glass Soul", SkyblockAbilityType.FULL_SET_BONUS,
                        Arrays.asList(ChatColor.GRAY
                                        + "Heavily increases your "
                                        + ChatColor.RED + SkyblockConstants.DAMAGE + " Damage",
                                ChatColor.GRAY + "but makes you able to",
                                ChatColor.GRAY + "die in just one hit!"))
        ));
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Between Life and Death"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("kasmir_boots");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 25, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", -10, AttributeModifier.Operation.ADD_NUMBER));
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }
}
