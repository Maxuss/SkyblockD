package space.maxus.skyblockd.discord;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import space.maxus.skyblockd.SkyblockD;

import java.awt.*;

public class InnerListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if(!e.getChannel().equals(SkyblockD.getDiscord().getChannel())) return;

        Member member = e.getMember();
        if(member == null || member.getUser().isBot()) return;

        Role role = member.getRoles().get(0);

        String prefix;
        if(role != null) {
            Color jcolor = role.getColor() == null ? Color.GRAY : role.getColor();
            net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of(jcolor);
            prefix = color+"["+role.getName()+"] ";
        } else {
            prefix = ChatColor.BLUE+"["+ChatColor.WHITE+"DISCORD"+"] ";
        }
        String message = e.getMessage().getContentDisplay();
        Bukkit.broadcastMessage(
                prefix+member.getEffectiveName()+ChatColor.WHITE+": "+message);

    }
}
