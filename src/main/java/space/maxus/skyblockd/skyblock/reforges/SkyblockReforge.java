package space.maxus.skyblockd.skyblock.reforges;

import space.maxus.skyblockd.skyblock.reforges.created.MagmaticReforge;
import space.maxus.skyblockd.skyblock.reforges.created.TangledReforge;
import space.maxus.skyblockd.skyblock.reforges.created.TestReforge;
import space.maxus.skyblockd.skyblock.reforges.created.WarpedReforge;

public enum SkyblockReforge {
    TEST("Testing", new TestReforge(), 0),
    MAGMATIC("Magmatic", new MagmaticReforge(), 1),
    TANGLED("Tangled", new TangledReforge(), 2),
    WARPED("Warped", new WarpedReforge(), 3),
    ;

    private ReforgeBase base;
    private String display;
    private int index;

    public ReforgeBase getBase() {
        return this.base;
    }
    public String getDisplayName() {
        return display;
    }

    SkyblockReforge(String displayName, ReforgeBase base, int index) {
        this.display = displayName;
        this.base = base;
        this.index = index;
    }

    public int getIndex() {return index;}

    public static SkyblockReforge byIndex(int index){
        if(index >= values().length || index < 0) return values()[0];
        return values()[index];
    }
}
