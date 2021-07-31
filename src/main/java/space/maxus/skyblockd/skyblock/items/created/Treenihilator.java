package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class Treenihilator extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_AXE,
                "Treenihilator",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.AXE,
                new SkyblockItemStats().setAttackSpeed(10)
        );
        SkyblockItemAbility abil = new SkyblockItemAbility("Netherite Power!", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"A powerful netherite axe capable", ChatColor.GRAY+"of chopping down whole forests!"));
        abil.setCooldown(2);
        cfg.setAbilities(
                Collections.singletonList(abil));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("super_axe_3");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.DIG_SPEED,3, true);
        m.addEnchant(Enchantment.DURABILITY, 3, true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("TREENIHILATOR"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
