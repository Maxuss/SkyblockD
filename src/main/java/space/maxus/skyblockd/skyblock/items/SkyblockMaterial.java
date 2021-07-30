package space.maxus.skyblockd.skyblock.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.created.*;
import space.maxus.skyblockd.skyblock.items.created.stones.ReforgeStone;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;
import space.maxus.skyblockd.skyblock.reforges.SkyblockReforge;

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

    ENCHANTED_PORKCHOP(new EnchantedItem("Enchanted Pork", Material.PORKCHOP, SkyblockRarity.UNCOMMON)),
    ENCHANTED_BEEF(new EnchantedItem("Enchanted Beef", Material.BEEF, SkyblockRarity.UNCOMMON)),
    ENCHANTED_CHICKEN(new EnchantedItem("Enchanted Chicken", Material.CHICKEN, SkyblockRarity.UNCOMMON)),
    ENCHANTED_RABBIT(new EnchantedItem("Enchanted Rabbit", Material.RABBIT, SkyblockRarity.UNCOMMON)),
    ENCHANTED_RABBIT_FOOT(new EnchantedItem("Enchanted Rabbit Foot", Material.RABBIT_FOOT, SkyblockRarity.UNCOMMON)),

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
    ENCHANTED_HONEYCOMB(new EnchantedItem("Enchanted Honeycomb", Material.HONEYCOMB,SkyblockRarity.UNCOMMON)),
    ENCHANTED_HONEYCOMB_BLOCK(new EnchantedItem("Enchanted Honeycomb Block", Material.HONEYCOMB_BLOCK,SkyblockRarity.RARE)),
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
    HARDWOOD(new EnchantedItem("Enchanted Hardwood", Material.ACACIA_LOG, SkyblockRarity.RARE)),
    GEMSTONE(new EnchantedItem("Gemstone", Material.DIAMOND, SkyblockRarity.RARE)),
    ENCHANTED_DARK_PRISMARINE(new EnchantedItem("Enchanted Dark Prismarine", Material.DARK_PRISMARINE, SkyblockRarity.EPIC)),
    SUPREME_STEW(new EnchantedItem("Supreme Stew", Material.RABBIT_STEW, SkyblockRarity.EPIC)),

    ASCENDED_ESSENCE(new AscendedEssence()),

    RECIPE_GUIDE_1(new EnchantedItemGuide()),
    RECIPE_GUIDE_2(new CompressedItemGuide()),
    RECIPE_GUIDE_GEO(new GeologistRecipeBook()),

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
    VAULT_KEY(new VaultKey()),

    LILY_BOOTS(new LilyBoots()),
    LILY_LEGGINGS(new LilyLeggings()),
    LILY_CHESTPLATE(new LilyChestplate()),
    LILY_HELMET(new LilyHelmet()),
    PRISMARINE_DAGGER(new PrismarineDagger()),

    TEST_SHORTBOW(new TestShortbow()),
    DRACONIC_SHORTBOW(new DraconicShortbow()),
    KASMIR_FURY(new KasmirFury()),
    EMERALD_SHORTBOW(new EmeraldShortbow()),
    GEMSTONE_SHORTBOW(new GemstoneShortbow()),
    HOLY_SHORTBOW(new HolyShortbow()),
    OBSIDIAN_SHORTBOW(new ObsidianShortbow()),

    KASMIR_HELMET(new KasmirHelmet()),
    KASMIR_CHESTPLATE(new KasmirChestplate()),
    KASMIR_LEGGINGS(new KasmirLeggings()),
    KASMIR_BOOTS(new KasmirBoots()),

    OBSIDIAN_HELMET(new ObsidianHelmet()),
    OBSIDIAN_CHESTPLATE(new ObsidianChestplate()),
    OBSIDIAN_LEGGINGS(new ObsidianLeggings()),
    OBSIDIAN_BOOTS(new ObsidianBoots()),
    
    YOUNG_DRAGON_HELMET(new YoungDragonHelmet()),
    YOUNG_DRAGON_CHESTPLATE(new YoungDragonChestplate()),
    YOUNG_DRAGON_LEGGINGS(new YoungDragonLeggings()),
    YOUNG_DRAGON_BOOTS(new YoungDragonBoots()),

    STRONG_DRAGON_HELMET(new StrongDragonHelmet()),
    STRONG_DRAGON_CHESTPLATE(new StrongDragonChestplate()),
    STRONG_DRAGON_LEGGINGS(new StrongDragonLeggings()),
    STRONG_DRAGON_BOOTS(new StrongDragonBoots()),

    SUPERIOR_DRAGON_HELMET(new SuperiorDragonHelmet()),
    SUPERIOR_DRAGON_CHESTPLATE(new SuperiorDragonChestplate()),
    SUPERIOR_DRAGON_LEGGINGS(new SuperiorDragonLeggings()),
    SUPERIOR_DRAGON_BOOTS(new SuperiorDragonBoots()),

    OLD_DRAGON_HELMET(new OldDragonHelmet()),
    OLD_DRAGON_CHESTPLATE(new OldDragonChestplate()),
    OLD_DRAGON_LEGGINGS(new OldDragonLeggings()),
    OLD_DRAGON_BOOTS(new OldDragonBoots()),

    UNSTABLE_DRAGON_HELMET(new UnstableDragonHelmet()),
    UNSTABLE_DRAGON_CHESTPLATE(new UnstableDragonChestplate()),
    UNSTABLE_DRAGON_LEGGINGS(new UnstableDragonLeggings()),
    UNSTABLE_DRAGON_BOOTS(new UnstableDragonBoots()),

    STRONG_FRAGMENT(new StrengthFragment()),
    YOUNG_FRAGMENT(new SwiftnessFragment()),
    SUPERIOR_FRAGMENT(new SuperiorityFragment()),
    OLD_FRAGMENT(new HealthFragment()),
    UNSTABLE_FRAGMENT(new ChaoticFragment()),

    DRAGON_SINGULARITY(new DragonSingularity()),
    ENERGETIC_HANDLE(new EnergeticHandle()),
    SHADED_EYE(new ShadedEye()),
    ASPECT_OF_THE_END(new AspectOfTheEnd()),
    SHORTBOW_BASE(new ShortbowBase()),

    TITAN_BOOTS(new TitanicBoots()),
    TITAN_LEGGINGS(new TitanicLeggings()),
    TITAN_CHESTPLATE(new TitanicChestplate()),
    TITAN_HELMET(new TitanicHelmet()),

    DEMETER(new Demeter()),

    LOST_CROWN(new LostCrown()),
    ERUMDIR_SOUL_FRAGMENT(new ErumdirSoulFragment()),

    KASMIR_SOUL_FRAGMENT(new KasmirSoulFragment()),
    NECRON_SOUL_FRAGMENT(new NecronSoulFragment()),
    GOLDOR_SOUL_FRAGMENT(new GoldorSoulFragment()),
    STORM_SOUL_FRAGMENT(new StormSoulFragment()),
    MAXOR_SOUL_FRAGMENT(new MaxorSoulFragment()),

    NECRON_BOOTS(new NecronBoots()),
    NECRON_LEGGINGS(new NecronLeggings()),
    NECRON_CHESTPLATE(new NecronChestplate()),
    NECRON_HELMET(new NecronHelmet()),

    STORM_BOOTS(new StormBoots()),
    STORM_LEGGINGS(new StormLeggings()),
    STORM_CHESTPLATE(new StormChestplate()),
    STORM_HELMET(new StormHelmet()),

    GOLDOR_BOOTS(new GoldorBoots()),
    GOLDOR_LEGGINGS(new GoldorLeggings()),
    GOLDOR_CHESTPLATE(new GoldorChestplate()),
    GOLDOR_HELMET(new GoldorHelmet()),

    MAXOR_BOOTS(new MaxorBoots()),
    MAXOR_LEGGINGS(new MaxorLeggings()),
    MAXOR_CHESTPLATE(new MaxorChestplate()),
    MAXOR_HELMET(new MaxorHelmet()),

    UNREFINED_BLADE(new UnrefinedBlade()),
    HYPERION(new Hyperion()),
    ASTRAEA(new Astraea()),
    SCYLLA(new Scylla()),
    VALKYRIE(new Valkyrie()),

    MUDSTONE_BOOTS(new MudstoneBoots()),
    MUDSTONE_LEGGINGS(new MudstoneLeggings()),
    MUDSTONE_CHESTPLATE(new MudstoneChestplate()),
    MUDSTONE_HELMET(new MudstoneHelmet()),

    THE_SEAL(new TheSeal()),

    STRONGHOLD(new ReforgeStone("Stronghold", SkyblockReforge.HARDENED, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiZDc4ZDY0ZjYwMjhhMjQxNjQxMTIzODk4Nzc4ODA2M2ZhZDY2OTFlODhmZTU1NGZiNWVjZWY0ZmYyNjcifX19").getItem()),
    CRYSTAL_BALL(new ReforgeStone("Crystal Ball", SkyblockReforge.PRISMATIC, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE1ZjI5YTc2ZDFmOTFjMTY1ZjYzYmFhYzA0ODY3MGU3YjFkMzdjZTc4NWE0ZDljMjFkOGMzYTE3N2I1In19fQ==").getItem()),
    BLESSED_ORB(new ReforgeStone("Blessed Orb", SkyblockReforge.BLESSED, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU4NmY1M2NmZWQ0MTJmMzU2MmVlZDI1NDUwNTBjMzQyMTBhNTY5ZDM0MTQwMjU1ZDM2ODUyMWFjYzFjNjkwOSJ9fX0=").getItem()),
    FORBIDDEN_BLOOD(new ReforgeStone("Forbidden Blood", SkyblockReforge.WITHERED, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA1MmU4NThkNjI3YzBmNDk1YmQyMmE2MjQzZjk5ZWJmYmQyZGRjMjRjNzBjYTE3MjUwN2NhN2Y4MGNmZGZhNCJ9fX0=").getItem()),
    MOLTEN_MAGMA(new ReforgeStone("Molten Magma", SkyblockReforge.MAGMATIC, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTlhNWExZTY5YjRmODEwNTYyNTc1MmJjZWUyNTM0MDY2NGIwODlmYTFiMmY1MjdmYTkxNDNkOTA2NmE3YWFkMiJ9fX0=").getItem()),
    MUTATED_ENDER_PEARL(new ReforgeStone("Mutated Ender Pearl", SkyblockReforge.WARPED, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWNiN2MyMWNjNDNkYzE3Njc4ZWU2ZjE2NTkxZmZhYWIxZjYzN2MzN2Y0ZjZiYmQ4Y2VhNDk3NDUxZDc2ZGI2ZCJ9fX0=").getItem()),
    TANGLED_SEAWEED(new ReforgeStone("Tangled Seaweed", SkyblockReforge.TANGLED, SkyblockRarity.RARE,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQyZTc4MGQwMjI0OTU1OWI1YTUzMWFiMjFlYTA0OWYxM2FiYzk2OWU3ZmRkYzhmZjMwZGM0NTU3YjYzOWU0ZCJ9fX0=").getItem()),
    MUTATED_FLESH(new ReforgeStone("Mutated Flesh", SkyblockReforge.MUTATED, SkyblockRarity.RARE,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1MDI1ODczZTNjNWU3NWY5YWNmYmNkZDA4ZDMxNWU0ODM1OWM1ODdiODdlZDNmY2NhMmNlZWYwYzQ3OTMzMSJ9fX0=").getItem()),
    HEART_OF_SEA(new ReforgeStone("Heart of the Sea", SkyblockReforge.SALTY, SkyblockRarity.EPIC,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTExM2VlNjEwODQxZGVkMjE1YWNkMmI0Y2FhZWVmODdkZmQ2ZTNkNDc2OGU3YWI0ZTE5ZWI3NmIzZDgxMjFjZiJ9fX0=").getItem()),
    SACRED_ORB(new ReforgeStone("Sacred Orb", SkyblockReforge.SACRED, SkyblockRarity.LEGENDARY,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5MTU5NDM2ZDIyMTIxNTI0MTg2ZWEzNTVlOWIxNWU3Mjk4YTZlYTcyODEyYTBhMzllYjU5ZTllYWUxMWNiNCJ9fX0=").getItem()),
    RUSTY_PLATING(new ReforgeStone("Rusty Plating", SkyblockReforge.RUSTY, SkyblockRarity.RARE,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc1MzA1NmVjYzEyYjY0MWJhNmE1Y2JkZjUwMzlkZWRlYzQ5MmFjNGZiNTgzNTQ1N2QzYTRlNDMyMmJkYjNkIn19fQ==").getItem()),
    SHADED_ORB(new ReforgeStone("Shaded Orb", SkyblockReforge.SHADY, SkyblockRarity.RARE,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTg3OWVkMmIzOWZhMDQ2MmM3NDI5MmY1Y2EzZDE4ODQyMDEyOGI0YTYzYWM3NWRiOGM5N2EwOTRkMWFjNjNmNCJ9fX0=").getItem()),
    DARK_MATTER(new ReforgeStone("Dark Matter", SkyblockReforge.ETHEREAL, SkyblockRarity.LEGENDARY,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI2OTc1YWY3MDcyNGQ2YTQ0ZmQ1OTQ2ZTYwYjI3MTc3MzdkZmRiNTQ1YjRkYWIxODkzMzUxYTljOWRkMTgzYyJ9fX0=").getItem()),
    CURSED_EYE(new ReforgeStone("Cursed Eye", SkyblockReforge.FEROCIOUS, SkyblockRarity.LEGENDARY,
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNlM2IxZjI2ZjY1MjVhZjgyZjViYTE0ZGJiOTNiNWU3YTU0M2Q0NzdmYThkYTEyM2RiZmFhNmQyMjlmZGE0ZiJ9fX0=").getItem()),


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
