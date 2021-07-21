package space.maxus.skyblockd.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.recipes.created.EnchantedDarkPrismarineRecipe;
import space.maxus.skyblockd.recipes.created.EnchantedEnderEyeRecipe;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRegisterer {
    public static final List<Material> normalEnchanted = Arrays.asList(
            Material.DIAMOND, Material.LAPIS_LAZULI, Material.COAL, Material.REDSTONE, Material.COBBLESTONE,
            Material.EMERALD, Material.IRON_INGOT, Material.GOLD_INGOT, Material.OBSIDIAN, Material.END_STONE,
            Material.NETHERITE_SCRAP, Material.DIRT, Material.CLAY_BALL, Material.GRAVEL, Material.SAND,
            Material.SOUL_SAND, Material.SOUL_SOIL, Material.POTATO, Material.CARROT, Material.WHEAT, Material.RED_MUSHROOM,
            Material.BROWN_MUSHROOM, Material.CHORUS_FLOWER, Material.NETHER_WART, Material.BEETROOT, Material.SUGAR_CANE,
            Material.CACTUS, Material.ROTTEN_FLESH, Material.BONE, Material.ENDER_PEARL, Material.STRING,
            Material.SPIDER_EYE, Material.PHANTOM_MEMBRANE, Material.GUNPOWDER, Material.GHAST_TEAR, Material.SLIME_BALL,
            Material.MAGMA_CREAM, Material.BLAZE_POWDER,
            Material.OAK_LOG, Material.DARK_OAK_LOG, Material.ACACIA_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG,
            Material.JUNGLE_LOG, Material.CRIMSON_STEM, Material.WARPED_STEM, Material.COD, Material.KELP,
            Material.TROPICAL_FISH, Material.SPONGE, Material.PRISMARINE_SHARD, Material.PRISMARINE_CRYSTALS,
            Material.INK_SAC, Material.PUFFERFISH, Material.SALMON, Material.QUARTZ
    );

    public static final HashMap<SkyblockMaterial, SkyblockMaterial> specialEnchanted = new HashMap<SkyblockMaterial, SkyblockMaterial>() {
        {
            put(SkyblockMaterial.ENCHANTED_DIAMOND, SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK);
            put(SkyblockMaterial.ENCHANTED_LAPIS_LAZULI, SkyblockMaterial.ENCHANTED_LAPIS_BLOCK);
            put(SkyblockMaterial.ENCHANTED_COAL, SkyblockMaterial.ENCHANTED_COAL_BLOCK);
            put(SkyblockMaterial.ENCHANTED_REDSTONE, SkyblockMaterial.ENCHANTED_REDSTONE_BLOCK);
            put(SkyblockMaterial.ENCHANTED_EMERALD, SkyblockMaterial.ENCHANTED_EMERALD_BLOCK);
            put(SkyblockMaterial.ENCHANTED_IRON_INGOT, SkyblockMaterial.ENCHANTED_IRON_BLOCK);
            put(SkyblockMaterial.ENCHANTED_GOLD_INGOT, SkyblockMaterial.ENCHANTED_GOLD_BLOCK);
            put(SkyblockMaterial.ENCHANTED_NETHERITE_SCRAP, SkyblockMaterial.ENCHANTED_NETHERITE);
            put(SkyblockMaterial.ENCHANTED_NETHERITE, SkyblockMaterial.ENCHANTED_NETHERITE_BLOCK);
            put(SkyblockMaterial.ENCHANTED_CLAY_BALL, SkyblockMaterial.ENCHANTED_CLAY_BLOCK);
            put(SkyblockMaterial.ENCHANTED_RED_MUSHROOM, SkyblockMaterial.ENCHANTED_RED_MUSHROOM_BLOCK);
            put(SkyblockMaterial.ENCHANTED_BROWN_MUSHROOM, SkyblockMaterial.ENCHANTED_BROWN_MUSHROOM_BLOCK);
            put(SkyblockMaterial.ENCHANTED_GHAST_TEAR, SkyblockMaterial.ENCHANTED_WISP);
            put(SkyblockMaterial.ENCHANTED_PHANTOM_MEMBRANE, SkyblockMaterial.ENCHANTED_PHANTOM_WING);
            put(SkyblockMaterial.ENCHANTED_BONE, SkyblockMaterial.ENCHANTED_BONE_BLOCK);
            put(SkyblockMaterial.ENCHANTED_MAGMA_CREAM, SkyblockMaterial.ENCHANTED_MAGMA_BLOCK);
            put(SkyblockMaterial.ENCHANTED_WHEAT, SkyblockMaterial.ENCHANTED_HAY);
            put(SkyblockMaterial.ENCHANTED_SLIME_BALL, SkyblockMaterial.ENCHANTED_SLIME_BLOCK);
            put(SkyblockMaterial.ENCHANTED_BLAZE_POWDER, SkyblockMaterial.ENCHANTED_BLAZE_ROD);
            put(SkyblockMaterial.ENCHANTED_KELP, SkyblockMaterial.ENCHANTED_DRIED_KELP);
            put(SkyblockMaterial.ENCHANTED_SALMON, SkyblockMaterial.ENCHANTED_COOKED_SALMON);
            put(SkyblockMaterial.ENCHANTED_COD, SkyblockMaterial.ENCHANTED_COOKED_COD);
            put(SkyblockMaterial.ENCHANTED_SPONGE, SkyblockMaterial.ENCHANTED_WET_SPONGE);
            put(SkyblockMaterial.ENCHANTED_QUARTZ, SkyblockMaterial.ENCHANTED_QUARTZ_BLOCK);

        }
    };

    public static void registerEnchantedItems() {
        int count = 0;
        for(Material mat : normalEnchanted) {
            new EnchantedItemRecipe(mat);
            count++;
        }
        SkyblockD.logger.info("Successfully registered " + count + " enchanted item recipes!");
        int specialCount = 0;
        for(Map.Entry<SkyblockMaterial, SkyblockMaterial> entry : specialEnchanted.entrySet()) {
            new EnchantedItemRecipe(entry.getKey().getItem(), entry.getValue());
            specialCount++;
        }
        ItemStack nether = new ItemStack(Material.NETHER_STAR);
        CustomItem.toSkyblockItem(nether);
        new EnchantedItemRecipe(nether, SkyblockMaterial.ENCHANTED_NETHER_STAR);
        new EnchantedItemRecipe(SkyblockMaterial.MOON_STONE.getItem(), SkyblockMaterial.ENCHANTED_MOON_STONE);
        new EnchantedEnderEyeRecipe();
        new EnchantedDarkPrismarineRecipe();
        specialCount += 4;
        SkyblockD.logger.info("Successfully registered " + specialCount + " extra enchanted item recipes!");
    }
}
