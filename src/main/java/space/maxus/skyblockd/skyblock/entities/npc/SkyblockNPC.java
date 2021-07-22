package space.maxus.skyblockd.skyblock.entities.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.GameModeTrait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public abstract class SkyblockNPC extends SkyblockEntity {
    public abstract Location getLocation(Entity e);

    public abstract Equipment setEquipment(Equipment base);

    public EntityEquipment getEquipment(EntityEquipment base) { return null; }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    public abstract String getName();

    public abstract double getHealth();

    public abstract double getDamage();

    public abstract double getDefense();

    public abstract int getLevel();

    public abstract String getSkyblockId();

    public abstract BehaviourType getBehaviour();

    public abstract void postInit(LivingEntity entity, Entity base);

    @Nullable
    public abstract String getSkinHash();
    @Nullable
    public abstract String getSkinSignature();

    @Override
    public Entity generate(Entity en) {

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, getName());

        SkinTrait trait = npc.getOrAddTrait(SkinTrait.class);
        trait.setSkinPersistent(getSkyblockId()+"_SKIN", getSkinSignature(), getSkinHash());

        npc.getOrAddTrait(GameModeTrait.class).setGameMode(GameMode.SURVIVAL);

        npc.spawn(getLocation(en));

        List<Entity> entities = npc.getEntity().getNearbyEntities(50d, 50d, 50d);

        if(!entities.isEmpty()) {
            Entity target = entities.get(0);
            for (Entity entity : entities) {
                if (entity instanceof Player && !entity.equals(npc.getEntity())) {
                    target = entity;
                }
            }
            npc.getNavigator().setTarget(target, true);
        }

        npc.setProtected(false);

        LivingEntity e = (LivingEntity) npc.getEntity();

        // set equipment

        npc.addTrait(setEquipment(npc.getOrAddTrait(Equipment.class)));

        // set attributes
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(getHealth());
        e.setHealth(getHealth());
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(getDamage());
        Objects.requireNonNull(e.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(getDefense());

        npc.setName(
                ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " +getLevel() + ChatColor.DARK_GRAY + "]" + " "
                        + getName() + ChatColor.RESET + " " + ChatColor.GREEN + getHealth() + ChatColor.WHITE
                        + "/" + ChatColor.GREEN + (int) getHealth() + ChatColor.RED + " " + SkyblockConstants.HEALTH
        );
        npc.setAlwaysUseNameHologram(true);

        e.addPotionEffect(PotionEffectType.SPEED.createEffect(1200, 3));

        addSkyblockTag(e);
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityName"), PersistentDataType.STRING, getName());
        e.getPersistentDataContainer().set(SkyblockD.getKey("entityLevel"), PersistentDataType.INTEGER, getLevel());


        // finish initialization by calling postInit
        postInit((LivingEntity) npc.getEntity(), en);

        return npc.getEntity();
    }
}
