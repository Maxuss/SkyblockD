package space.maxus.skyblockd.skyblock.entities;

import org.bukkit.entity.Entity;
import space.maxus.skyblockd.skyblock.entities.created.*;
import space.maxus.skyblockd.skyblock.entities.npc.created.EnderianZealot;

public enum EntitySummon {
    // NORMAL
    CORRUPTED_FANATIC(new CorruptedFanatic()),
    DUMMY(new Dummy()),
    ARMORED_DROWNED(new ArmoredDrowned()),
    OCEAN_RIDER(new OceanSailor()),
    DROWNED_REMNANT(new DrownedRemnant()),
    SEA_GUARDIAN(new SeaGuardian()),
    ETERNAL_SEAL(new EternalSeal()),
    KING_GUARDIAN(new KingGuardian()),
    SEA_ARCHER(new SeaArcher()),
    BUBBLING_PILLAGER(new BubblingPillager()),
    SEA_ELEPHANT(new SeaElephant()),
    BABY_WHALE(new BabyWhale()),
    SEA_TERROR(new SeaTerror()),
    LOST_DIVER(new LostDiver()),
    SEA_LEECH(new SeaLeech()),
    ATLANTIS(new Atlantis()),
    ENDSTONE_PROTECTOR(new EndstoneProtector()),

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
