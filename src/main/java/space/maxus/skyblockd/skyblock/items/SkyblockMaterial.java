package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.created.*;

public enum SkyblockMaterial {
    //#region Materials
    PURPUR_SWORD(new PurpurSword()),
    ENDSTONE_SWORD(new EndstoneSword()),
    ICARUS_WINGS(new IcarusWings()),
    MADNESS(new Madness()),
    RECOMBOBULATOR(new Recombobulator()),
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
    SKYBLOCK_MENU(SkyblockD.getItemManager().generated.get("skyblockd:SKYBLOCK_MENU"))
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
}
