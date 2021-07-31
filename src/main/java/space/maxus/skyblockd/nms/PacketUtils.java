package space.maxus.skyblockd.nms;

import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
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
}
