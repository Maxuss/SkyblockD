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

public class WorldDigester extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_SHOVEL,
                "World Digester",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.SHOVEL,
                new SkyblockItemStats()
        );
        cfg.setDescription(Arrays.asList(ChatColor.DARK_GRAY + "You won't be able to say 'Rock'",ChatColor.DARK_GRAY+"as I will be at bedrock!"));
        SkyblockItemAbility miner = new SkyblockItemAbility(
                "World Digester",
                SkyblockAbilityType.PASSIVE,
                Arrays.asList(
                        ChatColor.GRAY+"When digging blocks, have a chance",
                        ChatColor.GRAY+"to break more blocks around."));
        cfg.setAbilities(Collections.singletonList(miner));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("world_digester");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("WORLD_DIGESTER"), PersistentDataType.BYTE, (byte)0);
        i.setItemMeta(m);
        return i;
    }
}
