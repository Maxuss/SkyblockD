package space.maxus.skyblockd.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.items.CustomItem;
import space.maxus.skyblockd.recipes.created.*;
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
            Material.INK_SAC, Material.PUFFERFISH, Material.SALMON, Material.QUARTZ, Material.MELON_SLICE,
            Material.PUMPKIN, Material.BAMBOO, Material.LILY_PAD, Material.HONEYCOMB
    );

    public static final HashMap<SkyblockMaterial, SkyblockMaterial> specialEnchanted = new HashMap<>();

    static {
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_DIAMOND, SkyblockMaterial.ENCHANTED_DIAMOND_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_LAPIS_LAZULI, SkyblockMaterial.ENCHANTED_LAPIS_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_COAL, SkyblockMaterial.ENCHANTED_COAL_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_REDSTONE, SkyblockMaterial.ENCHANTED_REDSTONE_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_EMERALD, SkyblockMaterial.ENCHANTED_EMERALD_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_IRON_INGOT, SkyblockMaterial.ENCHANTED_IRON_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_GOLD_INGOT, SkyblockMaterial.ENCHANTED_GOLD_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_NETHERITE_SCRAP, SkyblockMaterial.ENCHANTED_NETHERITE);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_NETHERITE, SkyblockMaterial.ENCHANTED_NETHERITE_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_CLAY_BALL, SkyblockMaterial.ENCHANTED_CLAY_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_RED_MUSHROOM, SkyblockMaterial.ENCHANTED_RED_MUSHROOM_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_BROWN_MUSHROOM, SkyblockMaterial.ENCHANTED_BROWN_MUSHROOM_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_GHAST_TEAR, SkyblockMaterial.ENCHANTED_WISP);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_PHANTOM_MEMBRANE, SkyblockMaterial.ENCHANTED_PHANTOM_WING);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_BONE, SkyblockMaterial.ENCHANTED_BONE_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_MAGMA_CREAM, SkyblockMaterial.ENCHANTED_MAGMA_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_WHEAT, SkyblockMaterial.ENCHANTED_HAY);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_SLIME_BALL, SkyblockMaterial.ENCHANTED_SLIME_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_BLAZE_POWDER, SkyblockMaterial.ENCHANTED_BLAZE_ROD);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_KELP, SkyblockMaterial.ENCHANTED_DRIED_KELP);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_SALMON, SkyblockMaterial.ENCHANTED_COOKED_SALMON);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_COD, SkyblockMaterial.ENCHANTED_COOKED_COD);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_SPONGE, SkyblockMaterial.ENCHANTED_WET_SPONGE);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_QUARTZ, SkyblockMaterial.ENCHANTED_QUARTZ_BLOCK);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_MELON_SLICE, SkyblockMaterial.ENCHANTED_MELON);
        specialEnchanted.put(SkyblockMaterial.ENCHANTED_HONEYCOMB, SkyblockMaterial.ENCHANTED_HONEYCOMB_BLOCK);
    }

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
        specialCount += 4;
        SkyblockD.logger.info("Successfully registered " + specialCount + " extra enchanted item recipes!");

        String pkgName = RecipeRegisterer.class.getPackage().getName() + ".created";

        new EmeraldBootsRecipe();
        new EmeraldChestplateRecipe();
        new EmeraldLeggingsRecipe();
        new EmeraldHelmetRecipe();
        new EnchantedDarkPrismarineRecipe();
        new EnchantedEnderEyeRecipe();
        new FarmhandBlessingRecipe();
        new FarmhandGloryRecipe();
        new FarmhandSoulRecipe();
        new GrailRecipe();
        new Infusion2Recipe();
        new Infusion1Recipe();
        new PowderRecipe();
        new RecombobulatorRecipe();
        new RockPileRecipe();
        new SimpleGuideRecipe();
        new ComplexGuideRecipe();
        new LilyBootsRecipe();
        new LilyLeggingsRecipe();
        new LilyChestplateRecipe();
        new LilyHelmetRecipe();
        new PrismarineDaggerRecipe();
        new DragonSingularityRecipe();
        new AspectOfTheEndRecipe();
        new ThanathosRecipe();
        new GemstoneRecipe();

        new DragonSetRecipe("YOUNG");
        new DragonSetRecipe("STRONG");
        new DragonSetRecipe("SUPERIOR");

        new ShortbowRecipe("EMERALD_SHORTBOW", SkyblockMaterial.ENCHANTED_EMERALD);
        new ShortbowRecipe("OBSIDIAN_SHORTBOW", SkyblockMaterial.ENCHANTED_OBSIDIAN);
        new ShortbowRecipe("GEMSTONE_SHORTBOW", SkyblockMaterial.GEMSTONE);
        new ShortbowRecipe("HOLY_SHORTBOW", SkyblockMaterial.ENCHANTED_HONEYCOMB);

        new SimpleSetRecipe("OBSIDIAN", SkyblockMaterial.ENCHANTED_OBSIDIAN, null);

        new EnergeticHandleRecipe();
        new HardwoodRecipe();
        new ShortbowBaseRecipe();
    }
}
