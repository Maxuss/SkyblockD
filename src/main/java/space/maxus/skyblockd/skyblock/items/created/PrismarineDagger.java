package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class PrismarineDagger extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PRISMARINE_SHARD,
                "Prismarine Dagger",
                SkyblockRarity.UNCOMMON,
                SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(10).setDefense(25)
        );
        cfg.setAbilities(Collections.singletonList(new SkyblockItemAbility("Soaking", SkyblockAbilityType.PASSIVE,
                Arrays.asList(ChatColor.GRAY+"Deal double damage to", ChatColor.GRAY+"all mobs you have fished!"))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("prismarine_dagger");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 12, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier("generic.armor", 2.5, AttributeModifier.Operation.ADD_NUMBER));
        blockVanillaRecipes(m);
        i.setItemMeta(m);
        return i;
    }
}
