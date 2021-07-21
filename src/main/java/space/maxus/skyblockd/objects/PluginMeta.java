package space.maxus.skyblockd.objects;

import org.bukkit.ChatColor;

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

    public String getMotd() {
        return ChatColor.translateAlternateColorCodes('&', String.join("\n", motd));
    }
    public String getOfflineMotd() { return ChatColor.translateAlternateColorCodes('&', String.join("\n", offlineMotd)); }

    public class PluginVersion {
        public String nmsVer;
        public String mcVer;
    }
}
