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

public class Demeter extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GOLDEN_SWORD, "Demeter",
                SkyblockRarity.LEGENDARY, SkyblockItemType.SWORD,
                new SkyblockItemStats().setSpeed(-10)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Calm the Fierce!"));
        SkyblockItemAbility abil = new SkyblockItemAbility("God of Life", SkyblockAbilityType.RIGHT_CLICK,
                Arrays.asList(ChatColor.GRAY+"Heal yourself and all players",
                        ChatColor.GRAY+"around you for "+ChatColor.GREEN+"5%"+ChatColor.GRAY+" of your",
                        ChatColor.GRAY+"maximum Health."));
        abil.setCooldown(4);
        cfg.setAbilities(Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("demeter");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier("generic.movementSpeed", -0.01, AttributeModifier.Operation.ADD_NUMBER));
        m.getPersistentDataContainer().set(SkyblockD.getKey("DEMETER"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
