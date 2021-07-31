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

import java.util.Arrays;
import java.util.Collections;

public class NautilusChestplate extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.LEATHER_CHESTPLATE, "Nautilus Chestplate", SkyblockRarity.EPIC,
                SkyblockItemType.LEGGINGS, new SkyblockItemStats().setSeaCreatureChance(3)
                .setDefense(70).setHealth(20));
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
        return SkyblockD.getNamespace("nautilus_chestplate");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 2, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 7, AttributeModifier.Operation.ADD_NUMBER));
        addSeaCreatureChance(3, m);
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(242, 223, 213));
        i.setItemMeta(lm);
        return i;
    }
}
