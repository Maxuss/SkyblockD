package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class GeologistRecipeBook extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.KNOWLEDGE_BOOK,
                "Geologist's Guide", SkyblockRarity.RARE,
                SkyblockItemType.OTHER_NONCONSUMABLE, new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.GRAY+"Handy guide for geologists",
                ChatColor.GRAY+"willing to find out about",
                ChatColor.GRAY+"putting metals and gems to",
                ChatColor.GRAY+"right places and forms!"
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("geo_info_book");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        KnowledgeBookMeta meta = (KnowledgeBookMeta) i.getItemMeta();
        assert meta != null;
        meta.addRecipe(
                SkyblockD.getKey("enchanted_rock_pile_recipe"),
                SkyblockD.getKey("hardwood_recipe"),
                SkyblockD.getKey("gemstone_recipe"),
                SkyblockD.getKey("shortbow_base_recipe"),
                SkyblockD.getKey("gemstone_shortbow_recipe"),
                SkyblockD.getKey("obsidian_shortbow_recipe"),
                SkyblockD.getKey("obsidian_boots_recipe"),
                SkyblockD.getKey("obsidian_leggings_recipe"),
                SkyblockD.getKey("obsidian_chestplate_recipe"),
                SkyblockD.getKey("obsidian_helmet_recipe"),
                SkyblockD.getKey("titan_boots_recipe"),
                SkyblockD.getKey("titan_leggings_recipe"),
                SkyblockD.getKey("titan_chestplate_recipe"),
                SkyblockD.getKey("titan_helmet_recipe")
        );
        i.setItemMeta(meta);
        return i;
    }
}
