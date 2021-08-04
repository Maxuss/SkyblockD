package space.maxus.skyblockd.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.command.CommandSender;
import space.maxus.skyblockd.SkyblockD;

import java.awt.*;
import java.time.Instant;

@CommandInfo(name = "broadcast", permission = "skyblockd.admin", playerOnly = false)
public class BroadcastCommand extends ChatCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 1 || SkyblockD.getDiscord() == null) return false;

        String message = String.join(" ", args);

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor("SkyblockD Server", null,
                        "https://cdn.maxus.space/files/plugins/online.png")
                .setTimestamp(Instant.now())
                .setColor(Color.WHITE)
                .setFooter("Broadcast by "+sender.getName())
                .setDescription(message);

        SkyblockD.getDiscord().getChannel().sendMessage(eb.build()).queue();
        
        return true;
    }
}
