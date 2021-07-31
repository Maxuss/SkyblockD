package space.maxus.skyblockd.util;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class ConfigurationFile {

    private final @NotNull Path file;

    private final @NotNull Path dir;

    private final @NotNull YamlConfiguration yaml;

    public ConfigurationFile(@NotNull JavaPlugin plugin, @NotNull String name) {

        dir = plugin.getDataFolder().toPath();

        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        plugin.saveResource(name, false);
        file = dir.resolve(name);
        yaml = new YamlConfiguration();
        try {
            yaml.load(file.toString());
        } catch (@NotNull IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            yaml.save(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public @NotNull YamlConfiguration getYaml() {
        return yaml;
    }

    public @NotNull Path getDataPath() {
        return dir;
    }

    public boolean getBool(@NotNull String path) {
        return getYaml().getBoolean(path);
    }

    public int getInt(@NotNull String path) {
        return getYaml().getInt(path);
    }

    public @Nullable String getStr(@NotNull String path) {
        return getYaml().getString(path);
    }

    public @NotNull List<String> getStrList(@NotNull String path) {
        return getYaml().getStringList(path);
    }

    public @NotNull List<Boolean> getBoolList(@NotNull String path) {
        return getYaml().getBooleanList(path);
    }

    public @Nullable Object getObject(@NotNull String path, @NotNull Class<Object> classof) {
        return getYaml().getObject(path, classof);
    }
}
