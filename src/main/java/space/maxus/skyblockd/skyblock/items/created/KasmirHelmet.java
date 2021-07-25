package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;

public class KasmirHelmet extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "Kasmir Helmet",
                SkyblockRarity.RELIC, SkyblockItemType.HELMET,
                new SkyblockItemStats().setStrength(300).setHealth(-50)
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
    public String getSkyblockId() {
        return SkyblockD.getNamespace("kasmir_helmet");
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
                new AttributeModifier("generic.maxHealth", -10, AttributeModifier.Operation.ADD_NUMBER));
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjkzNDAzMDFhNjAxZjc0OTJhNjEwYzEwOWM0NTBjNDhhNDFjZjVlZTUwNTNmNWFiNzIxZTYxMmFiYTg0YWIyNiJ9fX0=";
    }
}
