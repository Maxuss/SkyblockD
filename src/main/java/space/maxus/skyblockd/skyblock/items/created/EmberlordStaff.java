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

public class EmberlordStaff extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.BLAZE_ROD, "Emberlord Staff", SkyblockRarity.EPIC,
                SkyblockItemType.SWORD, new SkyblockItemStats().setDamage(25).setAbilityDamage(25));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Inferno", SkyblockAbilityType.RIGHT_CLICK,
                        Arrays.asList(ChatColor.GRAY+"Create vortex of fire, damaging",
                                ChatColor.GRAY+"all your enemies!"))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("emberlord_staff");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("EMBERLORD_STAFF"), PersistentDataType.BYTE, (byte)0);
        ItemHelper.addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 25, m, EquipmentSlot.HAND);
        addAbilityDamage(25, m);
        i.setItemMeta(m);
        return i;
    }
}
