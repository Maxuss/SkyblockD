package space.maxus.skyblockd.listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.helpers.ItemHelper;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.events.SkyblockItemClickEvent;
import space.maxus.skyblockd.skyblock.items.AbilityStorage;
import space.maxus.skyblockd.skyblock.items.Shortbow;

import java.util.Objects;
import java.util.Random;

public class SkyblockClickListener extends BetterListener {
    private final static PersistentDataType<Byte, Byte> b = PersistentDataType.BYTE;

    @EventHandler
    public void onClick(SkyblockItemClickEvent ev) {
        PlayerInteractEvent e = ev.getPredcessor();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();

        if(i == null) return;

        if(Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer().has(SkyblockD.getKey("shortbow"), b)) {
            e.setCancelled(true);
            String idType = i.getItemMeta().getPersistentDataContainer().get(SkyblockD.getKey("shortbowType"), PersistentDataType.STRING);
            assert idType != null;
            Shortbow bow = Shortbow.getByID(idType);
            assert bow != null;
            Class<? extends Projectile> projectile = bow.getProjectileType();
            if(!ItemHelper.isOnCooldown(i, bow.getShootCooldown(), p, false)) {
                ItemMeta m = i.getItemMeta();
                if(m instanceof Damageable) {
                    Damageable dm = (Damageable) m;
                    dm.setDamage(dm.getDamage()-1);
                }
                Projectile pr = p.launchProjectile(projectile);
                pr.setVelocity(pr.getVelocity().add(p.getEyeLocation().getDirection()));
                if (pr instanceof Arrow) {
                    Arrow arr = (Arrow) pr;
                    arr.getPersistentDataContainer().set(SkyblockD.getKey("despawn"), PersistentDataType.BYTE, (byte) 0);
                    arr.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    arr.setKnockbackStrength(0);
                    arr.setDamage(bow.getArrowDamage() / 1.5f);
                    arr.setShooter(p);
                    switch(idType) {
                        case "GEMSTONE":
                            PotionEffect eff = getRandomEffect();
                            arr.addCustomEffect(getRandomEffect(), true);
                            break;
                        case "EMERALD":
                            arr.setVelocity(arr.getVelocity().add(p.getEyeLocation().getDirection()));
                            break;
                        case "OBSIDIAN":
                            arr.setVelocity(arr.getVelocity().multiply(0.5f));
                            break;
                        case "HOLY":
                            arr.getPersistentDataContainer().set(SkyblockD.getKey("heal"), PersistentDataType.BYTE, (byte)1);
                            break;
                        default:
                            break;
                    }
                } else if(pr instanceof Fireball) {
                    Fireball fb = (Fireball) pr;
                    fb.setIsIncendiary(false);
                    fb.setYield(0f);
                    fb.getPersistentDataContainer().set(SkyblockD.getKey("extraDamage"), PersistentDataType.DOUBLE, bow.getArrowDamage());
                    if(pr instanceof WitherSkull) {
                        WitherSkull ws = (WitherSkull) fb;
                        ws.setIsIncendiary(false);
                        ws.setYield(0);
                    }
                } else if(pr instanceof EnderPearl) {
                    EnderPearl ep = (EnderPearl) pr;
                    ep.addPassenger(p);
                }
            }
        }

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

            PersistentDataContainer c = Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
            if (c.has(SkyblockD.getKey("ETHEREAL_CRUSHER"), b)) {
                AbilityStorage.crusherAbility(i, p);
            } else if (c.has(SkyblockD.getKey("ASPECT_OF_DRAGON"), b)) {
                AbilityStorage.dragonAspectAbility(i, p);
            } else if (c.has(SkyblockD.getKey("THANATHOS"), b)) {
                AbilityStorage.thanathosAbility(i, p);
            } else if(c.has(SkyblockD.getKey("AOTE"), b)) {
                AbilityStorage.aoteAbility(p, i);
            }
        }
    }

    private PotionEffect getRandomEffect() {
        Random r = new Random();
        int type = r.nextInt(PotionEffectType.values().length);
        PotionEffectType effectType = PotionEffectType.values()[type];
        return new PotionEffect(effectType, (r.nextInt(2)+1)*100, r.nextInt(3)+1, true, false, true);
    }
}
