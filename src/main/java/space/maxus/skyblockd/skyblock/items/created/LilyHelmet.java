package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemStats;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemType;
import space.maxus.skyblockd.skyblock.objects.SkyblockRarity;

import java.util.Arrays;

public class LilyHelmet extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(Material.PLAYER_HEAD,
                "Lily Helmet", SkyblockRarity.RARE, SkyblockItemType.HELMET,
                new SkyblockItemStats().setHealth(25).setSeaCreatureChance(3));
        cfg.setDescription(Arrays.asList(ChatColor.DARK_GRAY+"Who knew lilies could be so", ChatColor.DARK_GRAY+"useful!"));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("lily_helmet");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addEnchant(Enchantment.THORNS, 3, true);
        m.setUnbreakable(true);
        m.getPersistentDataContainer().set(SkyblockD.getKey("scc"), PersistentDataType.INTEGER, 3);
        m.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth",
                        2.5,
                        AttributeModifier.Operation.ADD_NUMBER
                ));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QzODIxNjQ0YTE0ZTg2YWE0MjQ0OTYzODI0YmMzMzZlZDE3OWExOTMxYzE2NzY2YjM0NmY3MTYwNGQzM2E3In19fQ==";
    }
}
