package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.recipes.RecipeRegisterer;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;
import java.util.Map;

public class CompressedItemGuide extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.KNOWLEDGE_BOOK,
                "Intermediate Compressing Guide", SkyblockRarity.EPIC,
                SkyblockItemType.OTHER_NONCONSUMABLE, new SkyblockItemStats());
        cfg.setDescription(Arrays.asList(
                ChatColor.GRAY+"Handy guide for everyone",
                ChatColor.GRAY+"willing to find out about",
                ChatColor.GRAY+"compressing items even further",
                ChatColor.GRAY+"than just enchanted forms!"
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("info_book_2");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        KnowledgeBookMeta meta = (KnowledgeBookMeta) i.getItemMeta();
        assert meta != null;
        for(Map.Entry<SkyblockMaterial, SkyblockMaterial> entry : RecipeRegisterer.specialEnchanted.entrySet()) {
            SkyblockMaterial mat = entry.getValue();
            String recipeName = mat.name()+"_RECIPE";
            NamespacedKey key = SkyblockD.getKey(recipeName);
            meta.addRecipe(key);
        }
        i.setItemMeta(meta);
        return i;
    }
}
