package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeRegisterer;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class EnchantedItemGuide extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.KNOWLEDGE_BOOK,
                "Starter Compressing Guide", SkyblockRarity.RARE,
                SkyblockItemType.OTHER_NONCONSUMABLE, new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.GRAY+"Handy guide for everyone",
                ChatColor.GRAY+"willing to find out about",
                ChatColor.GRAY+"compressing items to enchanted",
                ChatColor.GRAY+"forms!"
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("info_book_1");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        KnowledgeBookMeta meta = (KnowledgeBookMeta) i.getItemMeta();
        assert meta != null;
        meta.addRecipe(RecipeRegisterer.normalEnchanted.stream().map(mat -> SkyblockD.getKey("ENCHANTED_" + mat.name() + "_RECIPE")).toArray(NamespacedKey[]::new));
        i.setItemMeta(meta);
        return i;
    }
}
