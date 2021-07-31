package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.GuiHelper;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.skyblock.utility.SkyblockFeature;

import java.util.Arrays;
import java.util.List;

public class SkyblockMenuItem extends CustomItem implements SkyblockFeature {

    @Override
    public @NotNull Material getMaterial() {
        return Material.NETHER_STAR;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public @NotNull ItemMeta generateMeta(@NotNull ItemMeta m) {
        m.setDisplayName(ChatColor.YELLOW + "Skyblock Menu");
        List<String> lore = Arrays.asList(
                ChatColor.GRAY + "Right click this item to open", ChatColor.GRAY + "Skyblock menu!"
        );
        m.setLore(lore);
        GuiHelper.setHideAllFlags(m);
        blockVanillaRecipes(m);
        m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        addSkyblockTag(m);
        return m;
    }

    @Override
    public @NotNull String getId() {
        return SkyblockD.getNamespace("skyblock_menu");
    }

    @Override
    public @NotNull String getSkyblockId() {
        return getId();
    }
}
