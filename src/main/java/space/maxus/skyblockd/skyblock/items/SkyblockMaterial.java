package space.maxus.skyblockd.skyblock.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.created.*;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

public enum SkyblockMaterial {
    //#region Materials

    MOON_STONE(new MoonStone()),
    ENCHANTED_MOON_STONE(new EnchantedMoonStone()),

    ERROR(new ErrorItem()),

    // MINING
    ENCHANTED_DIAMOND(new EnchantedItem("Enchanted Diamond", Material.DIAMOND, SkyblockRarity.RARE)),
    ENCHANTED_LAPIS_LAZULI(new EnchantedItem("Enchanted Lapis Lazuli", Material.LAPIS_LAZULI, SkyblockRarity.UNCOMMON)),
    ENCHANTED_COAL(new EnchantedItem("Enchanted Coal", Material.COAL, SkyblockRarity.UNCOMMON)),
    ENCHANTED_REDSTONE(new EnchantedItem("Enchanted Redstone", Material.REDSTONE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_COBBLESTONE(new EnchantedItem("Enchanted Cobblestone", Material.COBBLESTONE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_EMERALD(new EnchantedItem("Enchanted Emerald", Material.EMERALD, SkyblockRarity.UNCOMMON)),
    ENCHANTED_IRON_INGOT(new EnchantedItem("Enchanted Iron", Material.IRON_INGOT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_GOLD_INGOT(new EnchantedItem("Enchanted Gold", Material.GOLD_INGOT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_OBSIDIAN(new EnchantedItem("Enchanted Obsidian", Material.OBSIDIAN, SkyblockRarity.RARE)),
    ENCHANTED_END_STONE(new EnchantedItem("Enchanted End Stone", Material.END_STONE, SkyblockRarity.RARE)),
    ENCHANTED_ROCK_PILE(new EnchantedItem("Enchanted Rock Pile", Material.ANDESITE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_NETHERITE_SCRAP(new EnchantedItem("Enchanted Netherite Scrap", Material.NETHERITE_SCRAP, SkyblockRarity.RARE)),
    ENCHANTED_QUARTZ(new EnchantedItem("Enchanted Quartz", Material.QUARTZ, SkyblockRarity.UNCOMMON)),

    // EXCAVATING
    ENCHANTED_DIRT(new EnchantedItem("Enchanted Dirt", Material.DIRT, SkyblockRarity.COMMON)),
    ENCHANTED_GRAVEL(new EnchantedItem("Enchanted Gravel", Material.GRAVEL, SkyblockRarity.COMMON)),
    ENCHANTED_SAND(new EnchantedItem("Enchanted Sand", Material.SAND, SkyblockRarity.COMMON)),
    ENCHANTED_CLAY_BALL(new EnchantedItem("Enchanted Clay", Material.CLAY_BALL, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SOUL_SAND(new EnchantedItem("Enchanted Soul Sand", Material.SOUL_SAND, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SOUL_SOIL(new EnchantedItem("Enchanted Soul Soil", Material.SOUL_SOIL, SkyblockRarity.UNCOMMON)),

    // FARMING
    ENCHANTED_POTATO(new EnchantedItem("Enchanted Potatoes", Material.POTATO, SkyblockRarity.UNCOMMON)),
    ENCHANTED_CARROT(new EnchantedItem("Enchanted Carrots", Material.CARROT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_WHEAT(new EnchantedItem("Enchanted Wheat", Material.WHEAT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_RED_MUSHROOM(new EnchantedItem("Enchanted Red Mushroom", Material.RED_MUSHROOM, SkyblockRarity.UNCOMMON)),
    ENCHANTED_BROWN_MUSHROOM(new EnchantedItem("Enchanted Brown Mushroom", Material.BROWN_MUSHROOM, SkyblockRarity.UNCOMMON)),
    ENCHANTED_CHORUS_FLOWER(new EnchantedItem("Enchanted Chorus", Material.CHORUS_FLOWER, SkyblockRarity.RARE)),
    ENCHANTED_NETHER_WART(new EnchantedItem("Enchanted Nether Warts", Material.NETHER_WART, SkyblockRarity.UNCOMMON)),
    ENCHANTED_BEETROOT(new EnchantedItem("Enchanted Beetroots", Material.BEETROOT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SUGAR_CANE(new EnchantedItem("Enchanted Sugar Cane", Material.SUGAR_CANE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_CACTUS(new EnchantedItem("Enchanted Cactus", Material.CACTUS, SkyblockRarity.UNCOMMON)),
    ENCHANTED_MELON_SLICE(new EnchantedItem("Enchanted Melon Slice", Material.MELON_SLICE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_PUMPKIN(new EnchantedItem("Enchanted Pumpkin", Material.PUMPKIN, SkyblockRarity.UNCOMMON)),
    ENCHANTED_BAMBOO(new EnchantedItem("Enchanted Bamboo", Material.BAMBOO, SkyblockRarity.RARE)),

    // COMBAT
    ENCHANTED_ROTTEN_FLESH(new EnchantedItem("Enchanted Rotten Flesh", Material.ROTTEN_FLESH, SkyblockRarity.UNCOMMON)),
    ENCHANTED_ENDER_PEARL(new EnchantedItem("Enchanted Ender Pearl", Material.ENDER_PEARL, SkyblockRarity.RARE)),
    ENCHANTED_STRING(new EnchantedItem("Enchanted String", Material.STRING, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SPIDER_EYE(new EnchantedItem("Enchanted Spider Eye", Material.SPIDER_EYE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_PHANTOM_MEMBRANE(new EnchantedItem("Enchanted Phantom Membrane", Material.PHANTOM_MEMBRANE, SkyblockRarity.RARE)),
    ENCHANTED_GUNPOWDER(new EnchantedItem("Enchanted Gunpowder", Material.GUNPOWDER, SkyblockRarity.UNCOMMON)),
    ENCHANTED_GHAST_TEAR(new EnchantedItem("Enchanted Ghast Tear", Material.GHAST_TEAR, SkyblockRarity.RARE)),
    ENCHANTED_BONE(new EnchantedItem("Enchanted Bone", Material.BONE, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SLIME_BALL(new EnchantedItem("Enchanted Slime Ball", Material.SLIME_BALL, SkyblockRarity.UNCOMMON)),
    ENCHANTED_MAGMA_CREAM(new EnchantedItem("Enchanted Magma Cream", Material.MAGMA_CREAM, SkyblockRarity.RARE)),
    ENCHANTED_BLAZE_POWDER(new EnchantedItem("Enchanted Blaze Powder", Material.BLAZE_POWDER, SkyblockRarity.RARE)),

    // FORAGING
    ENCHANTED_OAK_LOG(new EnchantedItem("Enchanted Oak Log", Material.OAK_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_BIRCH_LOG(new EnchantedItem("Enchanted Birch Log", Material.BIRCH_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_SPRUCE_LOG(new EnchantedItem("Enchanted Spruce Log", Material.SPRUCE_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_ACACIA_LOG(new EnchantedItem("Enchanted Acacia Log", Material.ACACIA_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_DARK_OAK_LOG(new EnchantedItem("Enchanted Dark Oak Log", Material.DARK_OAK_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_JUNGLE_LOG(new EnchantedItem("Enchanted Jungle Log", Material.JUNGLE_LOG, SkyblockRarity.UNCOMMON)),
    ENCHANTED_WARPED_STEM(new EnchantedItem("Enchanted Warped Stem", Material.WARPED_STEM, SkyblockRarity.RARE)),
    ENCHANTED_CRIMSON_STEM(new EnchantedItem("Enchanted Crimson Stem", Material.CRIMSON_STEM, SkyblockRarity.RARE)),

    // FISHING
    ENCHANTED_COD(new EnchantedItem("Enchanted Cod", Material.COD, SkyblockRarity.UNCOMMON)),
    ENCHANTED_TROPICAL_FISH(new EnchantedItem("Enchanted Tropical Fish", Material.TROPICAL_FISH, SkyblockRarity.RARE)),
    ENCHANTED_SPONGE(new EnchantedItem("Enchanted Sponge", Material.SPONGE, SkyblockRarity.EPIC)),
    ENCHANTED_PRISMARINE_SHARD(new EnchantedItem("Enchanted Prismarine Shard", Material.PRISMARINE_SHARD, SkyblockRarity.UNCOMMON)),
    ENCHANTED_PRISMARINE_CRYSTALS(new EnchantedItem("Enchanted Prismarine Crystals", Material.PRISMARINE_CRYSTALS, SkyblockRarity.UNCOMMON)),
    ENCHANTED_INK_SAC(new EnchantedItem("Enchanted Ink Sac", Material.INK_SAC, SkyblockRarity.UNCOMMON)),
    ENCHANTED_PUFFERFISH(new EnchantedItem("Enchanted Pufferfish", Material.PUFFERFISH, SkyblockRarity.RARE)),
    ENCHANTED_SALMON(new EnchantedItem("Enchanted Salmon", Material.SALMON, SkyblockRarity.UNCOMMON)),
    ENCHANTED_KELP(new EnchantedItem("Enchanted Kelp", Material.KELP, SkyblockRarity.UNCOMMON)),
    ENCHANTED_LILY_PAD(new EnchantedItem("Enchanted Lily Pad", Material.LILY_PAD, SkyblockRarity.UNCOMMON)),

    // EXTRA
    ENCHANTED_HAY(new EnchantedItem("Enchanted Hay", Material.HAY_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_NETHERITE(new EnchantedItem("Enchanted Netherite", Material.NETHERITE_INGOT, SkyblockRarity.EPIC)),
    ENCHANTED_NETHERITE_BLOCK(new EnchantedItem("Enchanted Netherite Block", Material.NETHERITE_BLOCK, SkyblockRarity.LEGENDARY)),
    ENCHANTED_SLIME_BLOCK(new EnchantedItem("Enchanted Slime Block", Material.SLIME_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_MAGMA_BLOCK(new EnchantedItem("Enchanted Magma Block", Material.MAGMA_BLOCK, SkyblockRarity.EPIC)),
    ENCHANTED_BONE_BLOCK(new EnchantedItem("Enchanted Bone Block", Material.BONE_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_BLAZE_ROD(new EnchantedItem("Enchanted Blaze Rod", Material.BLAZE_ROD, SkyblockRarity.EPIC)),
    ENCHANTED_NETHER_STAR(new EnchantedItem("Star of Bethlehem", Material.NETHER_STAR, SkyblockRarity.LEGENDARY)),
    ENCHANTED_WISP(new EnchantedItem("Enchanted Wisp", Material.GHAST_TEAR, SkyblockRarity.EPIC)),
    ENCHANTED_PHANTOM_WING(new EnchantedItem("Enchanted Phantom Wing", Material.PHANTOM_MEMBRANE, SkyblockRarity.EPIC)),
    ENCHANTED_DIAMOND_BLOCK(new EnchantedItem("Enchanted Diamond Block", Material.DIAMOND_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_LAPIS_BLOCK(new EnchantedItem("Enchanted Lapis Block", Material.LAPIS_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_COAL_BLOCK(new EnchantedItem("Enchanted Coal Block", Material.COAL_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_REDSTONE_BLOCK(new EnchantedItem("Enchanted Redstone Block", Material.REDSTONE_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_EMERALD_BLOCK(new EnchantedItem("Enchanted Emerald Block", Material.EMERALD_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_IRON_BLOCK(new EnchantedItem("Enchanted Iron Block", Material.IRON_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_GOLD_BLOCK(new EnchantedItem("Enchanted Gold Block", Material.GOLD_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_CLAY_BLOCK(new EnchantedItem("Enchanted Clay Block", Material.CLAY, SkyblockRarity.RARE)),
    ENCHANTED_RED_MUSHROOM_BLOCK(new EnchantedItem("Enchanted Red Mushroom Block", Material.RED_MUSHROOM_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_BROWN_MUSHROOM_BLOCK(new EnchantedItem("Enchanted Brown Mushroom Block", Material.BROWN_MUSHROOM_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_COOKED_SALMON(new EnchantedItem("Enchanted Cooked Salmon", Material.COOKED_SALMON, SkyblockRarity.RARE)),
    ENCHANTED_COOKED_COD(new EnchantedItem("Enchanted Cooked Cod", Material.COOKED_COD, SkyblockRarity.RARE)),
    ENCHANTED_DRIED_KELP(new EnchantedItem("Enchanted Dried Kelp", Material.DRIED_KELP, SkyblockRarity.RARE)),
    ENCHANTED_WET_SPONGE(new EnchantedItem("Enchanted Wet Sponge", Material.WET_SPONGE, SkyblockRarity.LEGENDARY)),
    ENCHANTED_QUARTZ_BLOCK(new EnchantedItem("Enchanted Quartz Block", Material.QUARTZ_BLOCK, SkyblockRarity.RARE)),
    ENCHANTED_MELON(new EnchantedItem("Enchanted Melon Block", Material.MELON, SkyblockRarity.RARE)),

    // COMPLEX RECIPE
    ENCHANTED_EYE_OF_ENDER(new EnchantedItem("Enchanted Eye of Ender", Material.ENDER_EYE, SkyblockRarity.EPIC)),
    ENCHANTED_DARK_PRISMARINE(new EnchantedItem("Enchanted Dark Prismarine", Material.DARK_PRISMARINE, SkyblockRarity.EPIC)),

    RECIPE_GUIDE_1(new EnchantedItemGuide()),
    RECIPE_GUIDE_2(new CompressedItemGuide()),

    SKYBLOCK_MENU(SkyblockD.getItemManager().generated.get("skyblockd:SKYBLOCK_MENU")),

    PURPUR_SWORD(new PurpurSword()),
    ENDSTONE_SWORD(new EndstoneSword()),
    ICARUS_WINGS(new IcarusWings()),
    MADNESS(new Madness()),
    RECOMBOBULATOR(new Recombobulator()),
    RECOMBOBULATOR_CORE(new RecombobulatorCore()),
    SHADOW_FRACTURE_BOOTS(new ShadowFractureBoots()),
    SHADOW_FRACTURE_CHESTPLATE(new ShadowFractureChestplate()),
    SHADOW_FRACTURE_LEGGINGS(new ShadowFractureLeggings()),
    SHADOW_FRACTURE_HELMET(new ShadowFractureHelmet()),
    THE_STORM(new Storm()),
    TRANSMUTATION_POWDER(new CrystalPowder()),
    QUARTZ_INFUSION(new QuartzInfusion()),
    CRYSTAL_INFUSION(new CrystalInfusion()),
    GRAIL_1(new GrailPartOne()),
    GRAIL_2(new GrailPartTwo()),
    GRAIL_3(new GrailPartThree()),
    GRAIL_4(new GrailPartFour()),
    HOLY_GRAIL(new HolyGrail()),
    WITHER_JAR(new ContaminatedWither()),
    ETHEREAL_CRUSHER(new EtherealCrusher()),
    ASPECT_OF_DRAGON(new BreathOfDragon()),
    JUNGLE_AXE(new JungleAxe()),
    TREECAPITATOR(new Treecapitator()),
    TREENIHILATOR(new Treenihilator()),
    THANATHOS(new Thanathos()),
    THANATHOPHOBIA(new Thanathophobia()),
    EMERALD_BOOTS(new EmeraldBoots()),
    EMERALD_LEGGINGS(new EmeraldLeggings()),
    EMERALD_CHESTPLATE(new EmeraldChestplate()),
    EMERALD_HELMET(new EmeraldHelmet()),
    MAGIC_MAGNET(new MagicMagnet()),
    WORLD_DIGESTER(new WorldDigester()),
    PERSONAL_COMPACTOR(new HydraulicPress()),
    FARMHAND_SOUL(new FarmhandSoul()),
    FARMHAND_BLESSING(new FarmhandBlessing()),
    FARMHAND_GLORY(new FarmhandGlory()),
    ROD_OF_SEAS(new RodOfSeas()),

    LILY_BOOTS(new LilyBoots()),
    LILY_LEGGINGS(new LilyLeggings()),
    LILY_CHESTPLATE(new LilyChestplate()),
    LILY_HELMET(new LilyHelmet()),
    PRISMARINE_DAGGER(new PrismarineDagger()),

    //#endregion Materials
    ;

    private final ItemStack item;

    public ItemStack getItem() { return item; }

    SkyblockMaterial(SkyblockItem item) {
        this.item = item.generate();
    }

    SkyblockMaterial(ItemStack item) {
        this.item = item;
    }

    SkyblockMaterial(EnchantedItem item) {
        this.item = item.get();
    }
}
