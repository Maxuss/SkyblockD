package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkyblockItemConfig {
    private final Material material;
    private final @NotNull String name;
    private final @NotNull SkyblockRarity rarity;
    private final SkyblockItemType type;
    private final SkyblockItemStats stats;
    private @Nullable List<String> description = null;
    private @Nullable List<SkyblockItemAbility> abilities = null;

    public Material getMaterial() {return material;}
    public @NotNull String getName() {return name;}
    public @Nullable List<String> getDescription() { return description;}
    public @Nullable List<SkyblockItemAbility> getAbilities() { return abilities; }
    public @NotNull SkyblockRarity getRarity() { return rarity; }
    public SkyblockItemType getType() {return type;}
    public SkyblockItemStats getStats() { return stats; }

    public void setAbilities(List<SkyblockItemAbility> abilities) { this.abilities = abilities; }
    public void setDescription(List<String> description) { this.description = description; }

    public SkyblockItemConfig(
            Material mat,
            String itemname,
            @NotNull SkyblockRarity r,
            SkyblockItemType t,
            SkyblockItemStats s
    ){
        material = mat;
        name = r.displayColor+itemname;
        rarity = r;
        type = t;
        stats = s;
    }
}
