package space.maxus.skyblockd.skyblock.objects;

public class SkyblockItemStats {
    private int damage = 0;
    private int strength = 0;
    private int defense = 0;
    private int health = 0;
    private int intelligence = 0;
    private int critChance = 0;
    private int critDamage = 0;
    private int attackSpeed = 0;
    private int speed = 0;
    private int trueDefense = 0;
    private int seaCreatureChance = 0;
    private int magicFind = 0;
    private int petLuck = 0;
    private int ferocity = 0;

    public SkyblockItemStats setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public SkyblockItemStats setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public SkyblockItemStats setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public SkyblockItemStats setHealth(int health) {
        this.health = health;
        return this;
    }

    public SkyblockItemStats setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }

    public SkyblockItemStats setCritChance(int critChance) {
        this.critChance = critChance;
        return this;
    }

    public SkyblockItemStats setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public SkyblockItemStats setCritDamage(int critDamage) {
        this.critDamage = critDamage;
        return this;
    }

    public SkyblockItemStats setFerocity(int ferocity) {
        this.ferocity = ferocity;
        return this;
    }

    public SkyblockItemStats setMagicFind(int magicFind) {
        this.magicFind = magicFind;
        return this;
    }

    public SkyblockItemStats setPetLuck(int petLuck) {
        this.petLuck = petLuck;
        return this;
    }

    public SkyblockItemStats setSeaCreatureChance(int seaCreatureChance) {
        this.seaCreatureChance = seaCreatureChance;
        return this;
    }

    public SkyblockItemStats setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public SkyblockItemStats setTrueDefense(int trueDefense) {
        this.trueDefense = trueDefense;
        return this;
    }

    ///


    public int getCritChance() {
        return critChance;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getFerocity() {
        return ferocity;
    }

    public int getMagicFind() {
        return magicFind;
    }

    public int getPetLuck() {
        return petLuck;
    }

    public int getSeaCreatureChance() {
        return seaCreatureChance;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTrueDefense() {
        return trueDefense;
    }
}
