package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockItem;
import space.maxus.skyblockd.skyblock.objects.*;

import java.util.Arrays;
import java.util.Collections;

public class EtherealCrusher extends SkyblockItem {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.NETHERITE_PICKAXE,
                "Ethereal Crusher",
                SkyblockRarity.LEGENDARY,
                SkyblockItemType.PICKAXE,
                new SkyblockItemStats().setDefense(100)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY + "You won't be able to say 'Rock'\nas I will be at bedrock!"));
        SkyblockItemAbility hunger = new SkyblockItemAbility(
                "Block Hunger",
                SkyblockAbilityType.RIGHT_CLICK,
                Collections.singletonList(ChatColor.GRAY+"Break multiple blocks around you!"));
        hunger.setCooldown(3);
        SkyblockItemAbility miner = new SkyblockItemAbility(
                "Master Miner",
                SkyblockAbilityType.PASSIVE,
                Arrays.asList(
                        ChatColor.GRAY+"When mining blocks, have a chance",
                        ChatColor.GRAY+"to break more blocks around."));
        cfg.setAbilities(Arrays.asList(miner, hunger));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return false;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("ethereal_crusher");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_ARMOR,
                new AttributeModifier("generic.armor", 10, AttributeModifier.Operation.ADD_NUMBER));
        m.getPersistentDataContainer().set(SkyblockD.getKey("ETHEREAL_CRUSHER"), PersistentDataType.BYTE, (byte)1);
        i.setItemMeta(m);
        return i;
    }
}
