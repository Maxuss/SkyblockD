package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class AtlantisLeggings extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.LEATHER_LEGGINGS, "Atlantis Leggings",
                SkyblockRarity.RELIC, SkyblockItemType.LEGGINGS,
                new SkyblockItemStats().setDefense(100).setHealth(100).setSeaCreatureChance(6).setAbilityDamage(15));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Extinction", SkyblockAbilityType.PASSIVE,
                        Arrays.asList(ChatColor.GRAY+"All your spells and abilities",
                                ChatColor.GRAY+"deal "+ChatColor.RED+"+20% Damage"+ChatColor.GRAY+" against all",
                                ChatColor.GRAY+"sea creatures!"))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("atlantis_leggings");
    }

    @Override
    public @Nullable ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        ItemHelper.addAttribute(Attribute.GENERIC_ARMOR, 10, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, 10, m);
        addSeaCreatureChance(6, m);
        addAbilityDamage(15, m);
        LeatherArmorMeta lm = (LeatherArmorMeta) m;
        lm.setColor(Color.fromRGB(66, 212, 173));
        i.setItemMeta(lm);
        return i;
    }
}
