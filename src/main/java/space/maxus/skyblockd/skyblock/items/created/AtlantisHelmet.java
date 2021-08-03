package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class AtlantisHelmet extends SkyblockSkull {

    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(null, "Atlantis Helmet",
                SkyblockRarity.RELIC, SkyblockItemType.HELMET,
                new SkyblockItemStats().setDefense(80).setHealth(80).setSeaCreatureChance(6).setAbilityDamage(10));
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
        return SkyblockD.getNamespace("atlantis_helmet");
    }

    @Override
    public @Nullable ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        ItemHelper.addAttribute(Attribute.GENERIC_ARMOR, 8, m);
        ItemHelper.addAttribute(Attribute.GENERIC_MAX_HEALTH, 8, m);
        addSeaCreatureChance(6, m);
        addAbilityDamage(10, m);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public @NotNull String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFhNzMyNTI0MDFhNmU5NDZmNjFkYmFjMGUwMjdkMTgzZTBhY2U1ODc1MmZhMTVhNjRkMjQ0OWZjZjUwODdiNyJ9fX0=";
    }
}
