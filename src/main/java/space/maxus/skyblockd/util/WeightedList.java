package space.maxus.skyblockd.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderDragon;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.DragonLoot;
import space.maxus.skyblockd.objects.FishingDrops;
import space.maxus.skyblockd.objects.FishingMobs;
import space.maxus.skyblockd.objects.SeaCreatureContainer;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.items.SkyblockMaterial;

import java.util.*;

public class WeightedList<E> extends HashMap<E, Double> {
    private int total;

    public void put(E a, double b){
        super.put(a, b);
        total = 0;
        for(double in: values()) {
            total += in*1000;
        }
    }

    public E get(@NotNull Random rand){
        if (total <= 0) return null;
        int i = rand.nextInt(total);
        for(Entry<E, Double> entry: entrySet()){
            i -= (entry.getValue() * 1000);
            if (i < 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static @NotNull Singleton<WeightedList<ItemStack>, Boolean> fromFishingData(@NotNull FishingDrops items) {
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

    public static @NotNull Singleton<WeightedList<String>, Boolean> getFishingSummons(@NotNull FishingMobs mobs) {
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

    public static @NotNull WeightedList<ItemStack> getDragonDrops(@NotNull DragonLoot loot, boolean skyblock, float playerWeight, EnderDragon drag) {
        HashMap<String, Float> operated = loot.getVanilla();
        if(skyblock) operated = loot.getSkyblock();


        Random r = new Random();
        WeightedList<ItemStack> list = new WeightedList<>();
        if(Objects.requireNonNull(ChatColor.stripColor(drag.getCustomName())).contains("Absolute Dragon")) {
            SkyblockMaterial mat = SkyblockMaterial.FRONTIER;
            SkyblockMaterial ball = SkyblockMaterial.CRYSTAL_BALL;
            list.put(mat.getItem(), 0.3);
            list.put(ball.getItem(), 0.3);
        }
        for (Map.Entry<String, Float> entry: operated.entrySet()) {
            float v = entry.getValue();
            String k = entry.getKey();

            if(skyblock) {

                SkyblockMaterial mat = SkyblockMaterial.valueOf(k);
                int amount = r.nextInt(1)+1;
                ItemStack pble = mat.getItem().clone();
                pble.setAmount(amount);
                list.put(pble, v+playerWeight);
                continue;
            }
            int amount = r.nextInt(5)+2;
            ItemStack given = new ItemStack(Material.valueOf(k), amount);
            list.put(given, v+playerWeight);
        }

        return list;
    }

    public static @NotNull ItemStack getGuaranteedDrop(@NotNull DragonLoot loot) {
        List<String> operated = loot.getGuaranteed();
        Random r = new Random();
        int amount = r.nextInt(3)+1;
        int index = r.nextInt(operated.size());
        ItemStack chosen = SkyblockMaterial.valueOf(operated.get(index)).getItem().clone();
        chosen.setAmount(amount);
        return chosen;
    }

    public static @NotNull WeightedList<ItemStack> getProtectorDrops() {
        WeightedList<ItemStack> list = new WeightedList<>();
        ItemStack irons = SkyblockMaterial.ENCHANTED_IRON_INGOT.getItem();
        irons.setAmount(new Random().nextInt(2)+7);
        list.put(irons, 0.2d);
        list.put(SkyblockMaterial.ENCHANTED_IRON_BLOCK.getItem(), 0.2d);
        ItemStack eyes = SkyblockMaterial.ENCHANTED_EYE_OF_ENDER.getItem();
        eyes.setAmount(new Random().nextInt(1)+3);
        list.put(eyes, 0.2d);
        list.put(SkyblockMaterial.GOLEM_SWORD.getItem(), 0.1d);
        list.put(SkyblockMaterial.RECOMBOBULATOR_CORE.getItem(), 0.1d);
        list.put(SkyblockMaterial.DARK_MATTER.getItem(), 0.1d);
        list.put(SkyblockMaterial.ENCHANTED_MOON_STONE.getItem(), 0.05d);
        list.put(SkyblockMaterial.SHADED_EYE.getItem(), 0.1d);
        return list;
    }

    public static @NotNull WeightedList<ItemStack> getWitherDrops(SkyblockEntity.@NotNull WitherType type) {
        HashMap<String, HashMap<String, Float>> loot = SkyblockD.getServerData().witherLoot;
        String name = type.name();
        WeightedList<ItemStack> list = new WeightedList<>();
        for (Map.Entry<String, Float> entry: loot.get(name).entrySet()) {
            list.put(SkyblockMaterial.valueOf(entry.getKey()).getItem(), entry.getValue().doubleValue());
        }
        for (Map.Entry<String, Float> entry: loot.get("ALL").entrySet()) {
            list.put(SkyblockMaterial.valueOf(entry.getKey()).getItem(), entry.getValue().doubleValue());
        }
        return list;
    }

    public static @Nullable ItemStack getRareWitherDrop() {
        HashMap<String, HashMap<String, Float>> loot = SkyblockD.getServerData().witherLoot;
        HashMap<String, Float> loots = loot.get("EXTRA");
        WeightedList<String> pseudo = new WeightedList<>();
        for(Map.Entry<String, Float> entry : loots.entrySet()) {
            pseudo.put(entry.getKey(), entry.getValue());
        }
        String gend = pseudo.get(new Random());
        return gend == null ? null : SkyblockMaterial.valueOf(gend).getItem();
    }
}
