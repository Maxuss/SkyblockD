package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;

public class HolyGrail extends SkyblockSkull {
    @Override
    public SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "The Holy Grail",
                SkyblockRarity.LEGENDARY, SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats().setMagicFind(15).setHealth(100)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"...you were hidden from everyone..."));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Seven Seals", SkyblockAbilityType.RIGHT_CLICK,
                        Arrays.asList(
                                ChatColor.GRAY+"Blaze away your enemies by",
                                ChatColor.GRAY+"sending multiple fireballs at them.",
                                ChatColor.GRAY+"Fireballs deal "+ChatColor.GREEN+"10x"+ChatColor.GRAY+" of your",
                                ChatColor.AQUA+ SkyblockConstants.MAGIC_FIND+" Magic Find" + ChatColor.GRAY + " as true damage!"
                        ))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace("holy_grail");
    }

    @Override
    public ItemStack postInit(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10, AttributeModifier.Operation.ADD_NUMBER));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getSkinHash() {
        // https://minecraft-heads.com/custom-heads/decoration/3018-golden-chalice-with-gem-light-blue
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc1NGVlZDhkMDVhMTk2YzNmYzJkMjUxMTQxN2ViNTYyNjI2MjE0MTRjZTNiM2RmYjM1NzFhZWE0ZGRkYzQ3MCJ9fX0=";
    }
}
