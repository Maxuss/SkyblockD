package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Collections;

public class Storm extends SkyblockItem {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.DIAMOND_SWORD, "The Storm",
                SkyblockRarity.EPIC, SkyblockItemType.SWORD,
                new SkyblockItemStats().setDamage(10).setAttackSpeed(10)
                .setHealth(5)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"Furious"));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Fast strikes", SkyblockAbilityType.PASSIVE,
                        Collections.singletonList(ChatColor.GRAY+"Your Bonus Attack Speed is doubled!"))
        ));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("storm_item");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attackDamage", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 5, AttributeModifier.Operation.ADD_NUMBER));
        m.addAttributeModifier(
                Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier("generic.attackSpeed", 20*0.15, AttributeModifier.Operation.ADD_NUMBER));
        blockVanillaRecipes(m);
        i.setItemMeta(m);
        return i;
    }
}
