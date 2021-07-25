package space.maxus.skyblockd.skyblock.items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.items.created.*;
import space.maxus.skyblockd.skyblock.objects.SkyblockAbilityType;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemAbility;
import space.maxus.skyblockd.skyblock.objects.SkyblockItemConfig;

import java.util.Collections;

public abstract class Shortbow extends SkyblockItem {

    public abstract Class<? extends Projectile> getProjectileType();
    public abstract String getId();
    public abstract float getShootCooldown();
    public abstract double getArrowDamage();

    @Override
    public String getSkyblockId() {
        return SkyblockD.getNamespace(getId()+"_shortbow");
    }

    @Override
    public ItemStack generate() {

        ItemStack gend = super.generate();

        ItemMeta m = gend.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(SkyblockD.getKey("shortbow"), PersistentDataType.BYTE, (byte)0);
        m.getPersistentDataContainer().set(SkyblockD.getKey("shortbowType"), PersistentDataType.STRING, getId());
        gend.setItemMeta(m);

        return gend;
    }

    public static Shortbow getByID(String id) {
        switch (id) {
            case "TEST":
                return new TestShortbow();
            case "DRAGON":
                return new DraconicShortbow();
            case "KASMIR":
                return new KasmirFury();
            case "HOLY":
                return new HolyShortbow();
            case "GEMSTONE":
                return new GemstoneShortbow();
            case "EMERALD":
                return new EmeraldShortbow();
            case "OBSIDIAN":
                return new ObsidianShortbow();
            default:
                return null;
        }
    }

    protected void applyAbility(SkyblockItemConfig cfg) {
        SkyblockItemAbility sb = new SkyblockItemAbility("Shortbow", SkyblockAbilityType.PASSIVE, Collections.singletonList(ChatColor.GRAY+"Instantly shoots!"));
        if(cfg.getAbilities() != null) {
            cfg.getAbilities().add(sb);
        } else cfg.setAbilities(Collections.singletonList(sb));
    }
}
