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

public class JungleAxe extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.WOODEN_AXE,
                "Jungle Axe",
                SkyblockRarity.RARE,
                SkyblockItemType.AXE,
                new SkyblockItemStats().setAttackSpeed(10)
        );
        SkyblockItemAbility abil = new SkyblockItemAbility("Forest Power!", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"A powerful wooden axe capable", ChatColor.GRAY+"of breaking whole logs!"));
        abil.setCooldown(5);
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
        return SkyblockD.getNamespace("super_axe_1");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.DIG_SPEED,4, true);
        m.addEnchant(Enchantment.DURABILITY, 5, true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("JUNGLE_AXE"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
