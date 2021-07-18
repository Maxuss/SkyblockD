package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class Treecapitator extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GOLDEN_AXE,
                "Treecapitator",
                SkyblockRarity.EPIC,
                SkyblockItemType.AXE,
                new SkyblockItemStats().setAttackSpeed(10)
        );
        SkyblockItemAbility abil = new SkyblockItemAbility("Golden Power!", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"A powerful golden axe capable", ChatColor.GRAY+"of breaking whole trees!"));
        abil.setCooldown(4);
        cfg.setAbilities(
                Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("super_axe_2");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.DIG_SPEED,5, true);
        m.addEnchant(Enchantment.DURABILITY, 7, true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("TREECAPITATOR"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
