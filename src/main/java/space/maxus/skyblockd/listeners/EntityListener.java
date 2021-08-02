package space.maxus.skyblockd.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;

public class EntityListener extends BetterListener {
    @EventHandler
    public void onCreatureSpawn(@NotNull CreatureSpawnEvent e){
        LivingEntity le = e.getEntity();
        if(!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && !le.getPersistentDataContainer()
                .has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
            SkyblockEntity.toSkyblockEntity(le);
        }
    }
}
