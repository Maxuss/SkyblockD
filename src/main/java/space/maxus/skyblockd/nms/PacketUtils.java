package space.maxus.skyblockd.nms;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PacketUtils {
    public static <T extends Packet<?>> void sendPacket(@NotNull Player p, T packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendActionbar(@NotNull Player player, String message, NMSColor color) {
        IChatBaseComponent text = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+message+"\", "+color+"}");

        PacketPlayOutChat packet = new PacketPlayOutChat(text, ChatMessageType.GAME_INFO, player.getUniqueId());

        sendPacket(player, packet);
    }

    @NotNull
    public static CraftPlayer getCraftPlayer(@NotNull Player p) {
        return (CraftPlayer) p;
    }

    @NotNull
    public static <T extends Player> EntityPlayer getNMSPlayer(@NotNull T player) {
        return getCraftPlayer(player).getHandle();
    }
}
