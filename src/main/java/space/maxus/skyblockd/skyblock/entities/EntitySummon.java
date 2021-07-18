package space.maxus.skyblockd.skyblock.entities;

import org.bukkit.entity.Entity;
import space.maxus.skyblockd.skyblock.entities.created.CorruptedFanatic;
import space.maxus.skyblockd.skyblock.entities.created.Dummy;
import space.maxus.skyblockd.skyblock.entities.npc.created.EnderianZealot;

public enum EntitySummon {
    // NORMAL
    CORRUPTED_FANATIC(new CorruptedFanatic()),
    DUMMY(new Dummy()),

    // PLAYER NPC
    ENDERIAN_ZEALOT(new EnderianZealot())

    ;

    private final SkyblockEntity entity;

    public SkyblockEntity getEntity() { return entity; }
    public void summon(Entity base) { entity.generate(base); }

    EntitySummon(SkyblockEntity ent) {
        entity = ent;
    }
}
