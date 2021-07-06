package space.maxus.skyblockd.skyblock.objects;

import org.bukkit.Material;

import java.util.List;

public class SkyblockItemConfig {
    private final Material material;
    private final String name;
    private final SkyblockRarity rarity;
    private List<String> description = null;
    private List<SkyblockItemAbility> abilities = null;


    public Material getMaterial() {return material;}
    public String getName() {return name;}
    public List<String> getDescription() { return description;}
    public List<SkyblockItemAbility> getAbilities() { return abilities; }
    public SkyblockRarity getRarity() { return rarity; }

    public void setAbilities(List<SkyblockItemAbility> abilities) { this.abilities = abilities; }
    public void setDescription(List<String> description) { this.description = description; }

    public SkyblockItemConfig(
            Material mat,
            String itemname,
            SkyblockRarity r
    ){
        material = mat;
        name = r.displayColor+itemname;
        rarity = r;
    }
}
