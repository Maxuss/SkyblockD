package space.maxus.skyblockd.util;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Config extends ConfigurationFile {
    public Config(@NotNull JavaPlugin plugin, String name) {
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

    public boolean updateEachTime() { return getBool("skyblockd.extra.updateServerData"); }
}
