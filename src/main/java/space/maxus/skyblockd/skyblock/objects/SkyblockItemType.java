package space.maxus.skyblockd.skyblock.objects;

public enum SkyblockItemType {
    SWORD("SWORD", true, false),
    BOW("BOW", true, false),
    HELMET("HELMET", true, false),
    CHESTPLATE("CHESTPLATE", true, false),
    LEGGINGS("LEGGINGS", true, false),
    BOOTS("BOOTS", true, false),
    ACCESSORY("ACCESSORY", true, false),
    REFORGE_STONE("REFORGE STONE", false, true),
    FISHING_ROD("FISHING ROD", true, false),
    PICKAXE("PICKAXE", true, false),
    AXE("AXE", true, false),
    SHOVEL("SHOVEL", true, false),
    HOE("HOE", true, false),
    SHEARS("SHEARS", true, false),
    BREWING_INGREDIENT("BREWING INGREDIENT", false, true),
    COSMETIC("COSMETIC", false, true),
    PET_ITEM("PET ITEM", false, true),
    ELIXIR("ELIXIR", false, true),
    ELIXIR_INGREDIENT("ELIXIR INGREDIENT", false, true),
    SHORTBOW("SHORTBOW", true, false),

    OTHER_CONSUMABLE("", false, true),
    OTHER_NONCONSUMABLE("", false, false)
    ;

    private final String display;
    private final boolean isReforgeable;
    private final boolean isConsumable;

    public String getDisplay() {return display;}
    public boolean isReforgeable() {return isReforgeable;}
    public boolean isConsumable() {return isConsumable;}

    SkyblockItemType(String display, boolean isReforgeable, boolean isConsumable){
        this.display = display;
        this.isReforgeable = isReforgeable;
        this.isConsumable = isConsumable;
    }
}
