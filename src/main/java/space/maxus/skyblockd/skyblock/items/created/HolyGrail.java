package space.maxus.skyblockd.skyblock.items.created;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.SkyblockSkull;
import space.maxus.skyblockd.skyblock.objects.*;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Arrays;
import java.util.Collections;

public class HolyGrail extends SkyblockSkull {
    @Override
    public @NotNull SkyblockItemConfig getConfig() {
        SkyblockItemConfig cfg = new SkyblockItemConfig(
                Material.PLAYER_HEAD, "The Holy Grail",
                SkyblockRarity.RELIC, SkyblockItemType.OTHER_NONCONSUMABLE,
                new SkyblockItemStats().setMagicFind(15).setHealth(100)
        );
        cfg.setDescription(Collections.singletonList(ChatColor.DARK_GRAY+"...you were hidden from everyone..."));
        cfg.setAbilities(Collections.singletonList(
                new SkyblockItemAbility("Seven Seals", SkyblockAbilityType.RIGHT_CLICK,
                        Arrays.asList(
                                ChatColor.GRAY+"Blaze away your enemies by",
                                ChatColor.GRAY+"sending multiple fireballs at them.",
                                ChatColor.GRAY+"Fireballs deal "+ChatColor.GREEN+"10x"+ChatColor.GRAY+" of your",
                                ChatColor.LIGHT_PURPLE+ SkyblockConstants.ABILITY_DAMAGE+" Ability Damage" + ChatColor.GRAY + " as true damage!"
                        ))));
        return cfg;
    }

    @Override
    public boolean hasGlint() {
        return true;
    }

    @Override
    public @NotNull String getSkyblockId() {
        return SkyblockD.getNamespace("holy_grail");
    }

    @Override
    public @NotNull ItemStack postInit(@NotNull ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.addAttributeModifier(
                Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier("generic.maxHealth", 10, AttributeModifier.Operation.ADD_NUMBER));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public @NotNull String getSkinHash() {
        // https://minecraft-heads.com/custom-heads/decoration/3018-golden-chalice-with-gem-light-blue
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc1NGVlZDhkMDVhMTk2YzNmYzJkMjUxMTQxN2ViNTYyNjI2MjE0MTRjZTNiM2RmYjM1NzFhZWE0ZGRkYzQ3MCJ9fX0=";
    }
}
