package space.maxus.skyblockd.skyblock.items;

import org.bukkit.inventory.ItemStack;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.created.*;

public enum SkyblockMaterial {
    //#region Materials
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
    SKYBLOCK_MENU(SkyblockD.getItemManager().generated.get("skyblockd:SKYBLOCK_MENU"))
    //#endregion Materials
    ;

    private ItemStack item;

    public ItemStack getItem() { return item; }

    SkyblockMaterial(SkyblockItem item) {
        this.item = item.generate();
    }

    SkyblockMaterial(ItemStack item) {
        this.item = item;
    }
}
