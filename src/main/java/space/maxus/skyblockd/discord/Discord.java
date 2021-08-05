package space.maxus.skyblockd.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.Presence;
import org.bukkit.entity.Player;
import space.maxus.skyblockd.SkyblockD;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Objects;

public class Discord {
    private JDA bot;
    private TextChannel channel;
    private TextChannel bosses;
    private Presence botPresence;

    public JDA getBot() { return bot; }
    public TextChannel getChannel() { return channel; }
    public Presence getRichPresence() { return botPresence; }
    public TextChannel getBossChannel() { return bosses; }

    public Discord() {
        try {
            bot = JDABuilder.createDefault(SkyblockD.getCfg().discordToken()).build().awaitReady();
            channel = bot.getTextChannelById(Objects.requireNonNull(SkyblockD.getCfg().getStr("skyblockd.discord.channel")));
            bosses = bot.getTextChannelById(Objects.requireNonNull(SkyblockD.getCfg().getStr("skyblockd.discord.bosses")));

            new DiscordListener();
            bot.addEventListener(new InnerListener());

            botPresence = bot.getPresence();
            botPresence.setActivity(Activity.playing("SkyblockD"));

        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        bot.shutdown();
    }

    public void sendEmbedFromPlayer(Player player, String contents, boolean contentInAuthorLine, Color color) {
        if(channel == null) return;

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(
                        contentInAuthorLine ? contents : player.getName(),
                        null,
                        "https://crafatar.com/avatars/"+player.getUniqueId()+"?overlay=1"
                );
        if(!contentInAuthorLine) {
            eb.setDescription(contents);
        }
        eb.setColor(color);

        channel.sendMessage(eb.build()).queue();
    }

    public void sendEmbed(String message, Color color) {
        if(channel == null) return;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription(message);
        eb.setColor(color);

        channel.sendMessage(eb.build()).queue();
    }

    public void sendMessage(String message) {
        if(channel == null) return;

        channel.sendMessage(message).queue();
    }
}
