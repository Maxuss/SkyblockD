package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;

public class KasmirLeggings extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_LEGGINGS, "Kasmir Leggings",
                SkyblockRarity.RELIC, SkyblockItemType.LEGGINGS,
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
        return SkyblockD.getNamespace("kasmir_leggings");
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
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.BLACK);
        lm.setUnbreakable(true);
        i.setItemMeta(lm);
        return i;
    }
}
