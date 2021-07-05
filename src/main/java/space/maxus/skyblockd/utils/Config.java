package space.maxus.skyblockd.utils;

import org.bukkit.plugin.java.JavaPlugin;
import space.maxus.skyblockd.utils.ConfigurationFile;

public class Config extends ConfigurationFile {
    public Config(JavaPlugin plugin, String name) {
        super(plugin, name);
    }

    public boolean shoutEnabled() {
        return getBool("skyblockd.chat.shout.enabled");
    }

    public boolean shoutHasCooldown() {
        return getBool("skyblockd.chat.shout.hasCooldown");
    }

    public int shoutCooldown() {
        return getInt("skyblockd.chat.shout.cooldown");
    }

    public boolean ranksEnabled() {
        return getBool("skyblockd.chat.ranks.enabled");
    }

    public boolean ranksFancy() {
        return getBool("skyblockd.chat.ranks.interceptMessages");
    }

    public boolean inDevMode() {
        return getBool("skyblockd.commands.devmode");
    }
}
