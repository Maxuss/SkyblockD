package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class Madness extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                null, "The Madness", SkyblockRarity.LEGENDARY,
                SkyblockItemType.HELMET, new SkyblockItemStats().setHealth(-200)
                .setStrength(350)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY + "Your soul will be corrupted!"));
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility(
                "Shatter!", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"You reflect all damage you take,",
                        ChatColor.GRAY+"However your "+ChatColor.RED+ SkyblockConstants.HEALTH+" Health"+ChatColor.GRAY+" is reduced by",
                        ChatColor.GRAY+"a lot!"
                ))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("madness_item");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(),"generic.attackDamage", 35, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", -20, AttributeModifier.Operation.ADD_NUMBER));
        m.addEnchant(Enchantment.THORNS, 10, true);
        i.setItemMeta(m);
        return null;
    }

    @Override
    public String getSkinHash() {
        // see https://minecraft-heads.com/custom-heads/humanoid/35924-demon-king
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIxMDJiMzgyZmFmMTc4M2E5Zjk4NWYyYzlhMzhkNWE4MzM3MzA0MGQ1ZDAxN2NhMTUzNTRhNWMzNzA0OGE5MiJ9fX0=";
    }
}
