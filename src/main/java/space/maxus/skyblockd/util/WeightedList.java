package space.maxus.skyblockd.util;

import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.FishingDrops;
import space.maxus.skyblockd.objects.FishingMobs;
import space.maxus.skyblockd.objects.SeaCreatureContainer;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class WeightedList<E> extends HashMap<E, Double> {
    private int total;

    public void put(E a, double b){
        super.put(a, b);
        total = 0;
        for(double in: values()) {
            total += in*1000;
        }
    }

    @Nullable
    public E get(Random rand){
        if (total <= 0) return null;
        int i = rand.nextInt(total);
        SkyblockD.logger.info("#get Iteration new " + i);
        for(Entry<E, Double> entry: entrySet()){
            i -= (entry.getValue() * 1000);
            SkyblockD.logger.info("I:" + i);
            if (i < 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Singleton<WeightedList<ItemStack>, Boolean> fromFishingData(FishingDrops items) {
        double chance = new Random().nextDouble();
        HashMap<String, Double> operated;
        boolean isSkyblock = false;
        if(chance <= 0.4d) operated = items.vanilla;
        else {
            operated = items.skyblock;
            isSkyblock = true;
        }
        WeightedList<ItemStack> list = new WeightedList<>();

        if(isSkyblock) {
            for (Map.Entry<String, Double> entry : operated.entrySet()) {
                ItemStack item = SkyblockMaterial.valueOf(entry.getKey()).getItem();
                list.put(item, entry.getValue() * 1000);
            }
        } else {
            for (Map.Entry<String, Double> entry: operated.entrySet()) {
                ItemStack item = new ItemStack(Objects.requireNonNull(Material.valueOf(entry.getKey())), 1);
                list.put(item, entry.getValue() * 1000);
            }
        }

        return new Singleton<>(list, isSkyblock);
    }

    public static Singleton<WeightedList<String>, Boolean> getFishingSummons(FishingMobs mobs) {
        double randomChance = new Random().nextDouble();
        boolean isSkyblock = new Random().nextDouble() <= 0.6d;
        Singleton<WeightedList<String>, Boolean> singleton = new Singleton<>();
        singleton.setSecond(isSkyblock);

        WeightedList<String> list = new WeightedList<>();
        HashMap<String, SeaCreatureContainer> operated;
        if(isSkyblock) operated = mobs.skyblock;
        else operated = mobs.vanilla;

        for (Entry<String, SeaCreatureContainer> entry: operated.entrySet()) {
            SkyblockD.logger.info(entry.getKey()+":"+entry.getValue());
            list.put(entry.getKey(), entry.getValue().chance*1000);
        }

        singleton.setFirst(list);

        return singleton;
    }
}
