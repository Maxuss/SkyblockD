package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.skyblock.utility.StatPosition;

import java.util.Arrays;
import java.util.List;

public class SkyblockItemStats {
    private int damage = 0;
    private int strength = 0;
    private int defense = 0;
    private int health = 0;
    private int abilityDamage = 0;
    private int critChance = 0;
    private int critDamage = 0;
    private int attackSpeed = 0;
    private int speed = 0;
    private int trueDefense = 0;
    private int seaCreatureChance = 0;
    private int magicFind = 0;
    private int petLuck = 0;
    private int ferocity = 0;
    private int soulflow = 0;

    private static final ChatColor r = ChatColor.RED;
    private static final ChatColor g = ChatColor.GREEN;
    private static final ChatColor b = ChatColor.DARK_AQUA;
    //#region setters
    public @NotNull SkyblockItemStats setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public @NotNull SkyblockItemStats setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public @NotNull SkyblockItemStats setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public @NotNull SkyblockItemStats setHealth(int health) {
        this.health = health;
        return this;
    }

    public @NotNull SkyblockItemStats setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }

    public @NotNull SkyblockItemStats setCritChance(int critChance) {
        this.critChance = critChance;
        return this;
    }

    public @NotNull SkyblockItemStats setAbilityDamage(int intelligence) {
        this.abilityDamage = intelligence;
        return this;
    }

    public @NotNull SkyblockItemStats setCritDamage(int critDamage) {
        this.critDamage = critDamage;
        return this;
    }

    public @NotNull SkyblockItemStats setFerocity(int ferocity) {
        this.ferocity = ferocity;
        return this;
    }

    public @NotNull SkyblockItemStats setMagicFind(int magicFind) {
        this.magicFind = magicFind;
        return this;
    }

    public @NotNull SkyblockItemStats setPetLuck(int petLuck) {
        this.petLuck = petLuck;
        return this;
    }

    public @NotNull SkyblockItemStats setSeaCreatureChance(int seaCreatureChance) {
        this.seaCreatureChance = seaCreatureChance;
        return this;
    }

    public @NotNull SkyblockItemStats setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public @NotNull SkyblockItemStats setTrueDefense(int trueDefense) {
        this.trueDefense = trueDefense;
        return this;
    }

    public @NotNull SkyblockItemStats setSoulflow(int soulflow){
        this.soulflow = soulflow;
        return this;
    }
    //#endregion setters
    ///

    public boolean hasRedStats() {
        List<Integer> stats = Arrays.asList(
                damage, strength, attackSpeed, critChance,
                critDamage, seaCreatureChance
        );
        return stats.stream().anyMatch(i -> i != 0);
    }

    public boolean hasGreenStats() {
        List<Integer> stats = Arrays.asList(
                health, defense, speed, abilityDamage,
                magicFind, petLuck, trueDefense, ferocity, soulflow
        );
        return stats.stream().anyMatch(i -> i != 0);
    }
    //#region red
    @StatPosition(0)
    public @NotNull String getDamage() {
        return proccessStat("Damage", damage*5, r, false);
    }
    @StatPosition(1)
    public @NotNull String getStrength() {
        return proccessStat("Strength", strength, r, false);
    }
    @StatPosition(2)
    public @NotNull String getAttackSpeed() {
        return proccessStat("Bonus Attack Speed", attackSpeed, r, false);
    }
    @StatPosition(3)
    public @NotNull String getCritChance() {
        return proccessStat("Crit Chance", critChance, r, true);
    }
    @StatPosition(4)
    public @NotNull String getCritDamage() {
        return proccessStat("Crit Damage", critDamage, r, true);
    }
    @StatPosition(5)
    public @NotNull String getSeaCreatureChance() {
        return proccessStat("Sea Creature Chance", seaCreatureChance, r, true);
    }
    //#endregion red

    @StatPosition(6)
    public @NotNull String getReflectEmpty() { return " "; }

    @StatPosition(7)
    public @NotNull String getHealth() {
        return proccessStat("Health", health, g, false);
    }
    @StatPosition(8)
    public @NotNull String getDefense() {
        return proccessStat("Defense", defense, g, false);
    }
    @StatPosition(9)
    public @NotNull String getSpeed() {
        return proccessStat("Speed", speed, g, false);
    }
    @StatPosition(10)
    public @NotNull String getIntelligence() { return proccessStat("Ability Damage", abilityDamage, g, true); }
    @StatPosition(11)
    public @NotNull String getMagicFind() {
        return proccessStat("Magic Find", magicFind, g, false);
    }
    @StatPosition(12)
    public @NotNull String getPetLuck() {
        return proccessStat("Pet Luck", petLuck, g, false);
    }
    @StatPosition(13)
    public @NotNull String getTrueDefense() {
        return proccessStat("True Defense", trueDefense, g, false);
    }
    @StatPosition(14)
    public @NotNull String getFerocity() {
        return proccessStat("Ferocity", ferocity, g, false);
    }
    @StatPosition(15)
    public @NotNull String getSoulflow() { return proccessStat("Soulflow", soulflow, b, false); }

    private @NotNull String proccessStat(String name, int amount, ChatColor color, boolean percented){
        return amount != 0 ? ChatColor.GRAY + name + ": " + color +(amount < 0 ? amount : "+" +amount) + (percented ? "%" : ""): "";
    }
}
