package space.maxus.skyblockd.skyblock.events;

import org.bukkit.event.block.BlockBreakEvent;

public class SkyblockBlockBreakEvent extends SkyblockEvent<BlockBreakEvent> {
    private final BlockBreakEvent predcessor;

    @Override
    public BlockBreakEvent getPredcessor() {
        return predcessor;
    }

    public SkyblockBlockBreakEvent(BlockBreakEvent e){
        predcessor = e;
    }
}
