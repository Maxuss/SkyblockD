package space.maxus.skyblockd.helpers;

import com.google.gson.reflect.TypeToken;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.elixirs.ElixirEffect;
import space.maxus.skyblockd.skyblock.elixirs.created.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;

public class MaterialHelper {
    public static Material fromString(String mat){
        return Material.getMaterial(mat);
    }

    public static HashMap<String, Integer> getExperienceTable(TableType t) throws IOException {
        HashMap<String,Integer> map = new HashMap<>();
        @SuppressWarnings("unchecked")
        JsonHelper<HashMap<String, Integer>> j = new JsonHelper<>((Class<HashMap<String, Integer>>) map.getClass(), true);
        Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
        return j.deserializeJson(JsonHelper.readJsonResource("skilltables/"+t.filename),type);
    }

    public static int matchTable(TableType type, String material) throws IOException {
        HashMap<String, Integer> data = getExperienceTable(type);
        if(!data.containsKey(material)) return 0;
        return data.get(material);
    }

    public static int matchTable(TableType type, Material material) throws IOException {
        HashMap<String, Integer> data = getExperienceTable(type);
        String tt = material.name();
        if(!data.containsKey(tt)) return 0;
        return data.get(tt);
    }

    public static int matchCombat(EntityType t){
        switch(t){
            case ENDER_DRAGON:
            case WITHER: return 900;

            case ENDERMITE:
            case STRAY:
            case PLAYER:
            case DROWNED:
            case MAGMA_CUBE:
            case POLAR_BEAR:
            case ZOMBIE_VILLAGER:
                return 15;

            case VEX:
            case WITCH:
            case PIGLIN:
            case CREEPER:
            case PILLAGER:
                return 20;

            case HUSK:
            case SLIME:
            case ZOMBIE:
            case SPIDER:
            case PHANTOM:
                return 10;

            case GHAST:
            case RAVAGER:
                return 70;

            case GIANT:
            case ELDER_GUARDIAN:
                return 100;

            case EVOKER:
            case ZOGLIN:
                return 50;

            case HOGLIN:
            case GUARDIAN:
            case ILLUSIONER:
            case WITHER_SKELETON:
                return 25;

            case IRON_GOLEM:
            case VINDICATOR:
                return 35;

            case ARMOR_STAND:
            case AREA_EFFECT_CLOUD:
            case PAINTING:
            case LEASH_HITCH:
            case ARROW:
            case TRIDENT:
            case MINECART:
            case ITEM_FRAME:
                return 0;

            case SILVERFISH:
            case RABBIT:
                return 3;
            case CAVE_SPIDER: return 17;
            case PIGLIN_BRUTE: return 60;
            case BLAZE: return 30;
            case ENDERMAN: return 40;
            case SHULKER: return 45;
            case SKELETON: return 12;
            case FOX: return 7;
            case PANDA: return 2;

            default:
                return 5;
        }
    }

    public static boolean isMaterialLog(Material mat) {
        return mat.name().endsWith("_LOG") || mat.name().endsWith("_STEM");
    }

    public static boolean isMaterialStone(Material mat) {
        switch(mat){
            case GRANITE:
            case ANDESITE:
            case DIORITE:
            case END_STONE:
            case ANCIENT_DEBRIS:
            case OBSIDIAN:
            case STONE:
            case COBBLESTONE: return true;

            default: return mat.name().endsWith("_ORE");
        }
    }

    public static boolean isMaterialDirt(Material mat) {
        switch(mat){
            case CLAY:
            case SOUL_SOIL:
            case SOUL_SAND:
            case GRAVEL:
            case SAND:
            case DIRT: return true;

            default: return false;
        }
    }

    public static boolean isMaterialPlant(Material mat) {
        switch(mat) {
            case BEETROOTS:
            case NETHER_WART:
            case CHORUS_FLOWER:
            case CHORUS_PLANT:
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
            case WHEAT:
            case CARROTS:
            case POTATOES:
            case SUGAR_CANE: return true;

            default: return false;
        }
    }

    public static ElixirEffect getEffect(ItemStack i, ItemStack potion) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        if(m.getPersistentDataContainer().has(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING)) {
            String type = m.getPersistentDataContainer().get(SkyblockD.getKey("elixirEffect"), PersistentDataType.STRING);
            switch(Objects.requireNonNull(type)) {
                case "insanity":
                    return new InsanityEffect(potion);
                case "fury":
                    return new FuryEffect(potion);
                case "berserk":
                    return new BerserkEffect(potion);
                case "fleet":
                    return new FleetEffect(potion);
                case "digger":
                    return new DiggerEffect(potion);
                case "paralysis":
                    return new ParalysisEffect(potion);
                case "yeti":
                    return new YetiEffect(potion);
                case "dragon":
                    return new DragonEffect(potion);
                default: return new FailedEffect(potion);
            }
        }
        else {
            Material type = i.getType();
            switch(type) {
                case IRON_PICKAXE:
                case IRON_INGOT:
                case COCOA:
                case DIAMOND_PICKAXE:
                case BAMBOO:
                case GLOWSTONE:
                case REDSTONE:
                    return new DiggerEffect(potion);

                case NETHERITE_SCRAP:
                case DIAMOND:
                case SUGAR_CANE:
                case SUGAR:
                    return new FleetEffect(potion);

                case TRIPWIRE_HOOK:
                case CREEPER_HEAD:
                case SPONGE:
                case COD:
                case GUNPOWDER:
                case TNT:
                    return new InsanityEffect(potion);

                case BLAZE_POWDER:
                case BLAZE_ROD:
                case EMERALD_BLOCK:
                case BARRIER:
                case NETHERITE_SWORD:
                    return new BerserkEffect(potion);

                case FIRE_CHARGE:
                case GOLD_INGOT:
                case NETHERITE_INGOT:
                case NETHERITE_AXE:
                case DIAMOND_BLOCK:
                    return new FuryEffect(potion);

                case BEACON:
                case DRAGON_EGG:
                case DRAGON_HEAD:
                case DRAGON_BREATH:
                case DEAD_FIRE_CORAL:
                case FIRE_CORAL:
                case DIAMOND_SWORD:
                    return new DragonEffect(potion);

                case TURTLE_EGG:
                case ANVIL:
                case TURTLE_HELMET:
                case SHULKER_SHELL:
                case NAUTILUS_SHELL:
                    return new ParalysisEffect(potion);

                case ICE:
                case LIGHT_BLUE_WOOL:
                case SNOW:
                case PACKED_ICE:
                case BLUE_ICE:
                case SNOW_BLOCK:
                    return new YetiEffect(potion);


                default: return new FailedEffect(potion);
            }
        }
    }

    public enum TableType {
        FARMING ("farming.json"),
        FORAGING ("foraging.json"),
        MINING ("mining.json"),
        EXCAVATING("excavating.json")
        ;

        public String filename;

        TableType(String file){
            filename = file;
        }
    }
}
