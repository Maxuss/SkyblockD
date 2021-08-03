package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class EmberRod extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.BLAZE_ROD, "Ember Rod", SkyblockRarity.RARE,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(10).setAbilityDamage(10));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Fiery Shower", SkyblockAbilityType.RIGHT_CLICK,
                        Arrays.asList(ChatColor.GRAY+"Fire 3 powerful fireballs at",
                                ChatColor.GRAY+"your enemies!",
                                " ",
                                ChatColor.RED+""+ ChatColor.BOLD+"WARNING! "+ChatColor.RESET+ChatColor.RED+"Can break blocks!"))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("ember_rod");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("EMBER_ROD"), PersistentDataType.BYTE, (byte)0);
        ItemHelper.addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 10, m, EquipmentSlot.HAND);
        addAbilityDamage(10, m);
        i.setItemMeta(m);
        return i;
    }
}
