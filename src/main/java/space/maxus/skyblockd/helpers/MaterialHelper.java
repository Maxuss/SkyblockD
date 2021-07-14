package space.maxus.skyblockd.helpers;

import com.google.gson.reflect.TypeToken;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

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
                return 0;

            case SILVERFISH: return 3;
            case CAVE_SPIDER: return 17;
            case PIGLIN_BRUTE: return 60;
            case BLAZE: return 30;
            case ENDERMAN: return 40;
            case SHULKER: return 45;
            case SKELETON: return 12;

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
