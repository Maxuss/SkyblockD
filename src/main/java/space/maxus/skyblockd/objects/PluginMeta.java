package space.maxus.skyblockd.objects;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PluginMeta {
    public String version;
    public String fullVersion;
    public boolean beta;
    public String downloadLink;
    public String codename;
    public List<PluginVersion> supportedVersions;
    public List<String> motd;
    public List<String> offlineMotd;
    public String pluginName;

    public @NotNull String getMotd() {
        return ChatColor.translateAlternateColorCodes('&', String.join("\n", motd));
    }
    public @NotNull String getOfflineMotd() { return ChatColor.translateAlternateColorCodes('&', String.join("\n", offlineMotd)); }

    public static class PluginVersion {
        public String nmsVer;
        public String mcVer;
    }
}
