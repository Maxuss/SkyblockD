package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.meta.ItemMeta;

public interface StatContainer {
    void applyStat(String statName, int amount, ItemMeta m);

    void addFarmingFortune(int amount, ItemMeta m);
    void addMiningFortune(int amount, ItemMeta m);
    void addExcavatingFortune(int amount, ItemMeta m);
    void addSeaCreatureChance(int amount, ItemMeta m);
    void addAbilityDamage(int amount, ItemMeta m);
    void addStrength(int amount, ItemMeta m);
}
