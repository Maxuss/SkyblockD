package space.maxus.skyblockd.listeners;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import space.maxus.skyblockd.SkyblockD;
import space.maxus.skyblockd.objects.BetterListener;
import space.maxus.skyblockd.skyblock.entities.SkyblockEntity;
import space.maxus.skyblockd.skyblock.utility.SkyblockConstants;

import java.util.Objects;

public class EntityListener extends BetterListener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e){
        LivingEntity le = e.getEntity();
        if(le.hasMetadata("NPC")) {
            NPC npc = SkyblockD.getNpcRegistry().getNPC(le);
            int lvl = (int) (Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() / 2);
            String n = npc.getName();
            String name = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY +"Lv "+ lvl + ChatColor.DARK_GRAY + "]" + " "
                    + n + ChatColor.RESET + " " + ChatColor.GREEN + (int)le.getHealth() + ChatColor.WHITE
                    + "/" + ChatColor.GREEN +
                    (int) Objects.requireNonNull(le.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue()
                    + ChatColor.RED + "" + SkyblockConstants.HEALTH;
            npc.setName(name);
        }
        if(!le.getPersistentDataContainer()
                .has(SkyblockD.getKey("skyblockNative"), PersistentDataType.STRING)) {
            SkyblockEntity.toSkyblockEntity(le);
        }
    }
}
