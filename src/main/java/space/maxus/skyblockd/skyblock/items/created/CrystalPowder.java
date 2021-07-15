package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class CrystalPowder extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.GUNPOWDER, "Crystal Powder", SkyblockRarity.EPIC,
                SkyblockItemType.OTHER_CONSUMABLE, new SkyblockItemStats());
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Transmutation!", SkyblockAbilityType.PASSIVE,
                        Arrays.asList(ChatColor.GRAY+"Use this item on transmutation cauldron",
                                ChatColor.GRAY+"to brew ingredients into "+ ChatColor.LIGHT_PURPLE+ "elixirs"+ChatColor.GRAY+"!",
                                ChatColor.GRAY+"Different ingredients provide different",
                                ChatColor.GRAY+"powers for your elixir!"))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("crystal_powder");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("itemType"), PersistentDataType.STRING, "crystal_powder");
        i.setItemMeta(m);
        return i;
    }
}
