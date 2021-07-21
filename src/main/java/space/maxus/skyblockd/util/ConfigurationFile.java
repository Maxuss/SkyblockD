package space.maxus.skyblockd.util;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class ConfigurationFile {

    private final Path file;

    private final Path dir;

    private final YamlConfiguration yaml;

    public ConfigurationFile(JavaPlugin plugin, String name) {

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
        } catch (IOException | InvalidConfigurationException e) {
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

    public YamlConfiguration getYaml() {
        return yaml;
    }

    public Path getDataPath() {
        return dir;
    }

    public boolean getBool(String path) {
        return getYaml().getBoolean(path);
    }

    public int getInt(String path) {
        return getYaml().getInt(path);
    }

    public String getStr(String path) {
        return getYaml().getString(path);
    }

    public List<String> getStrList(String path) {
        return getYaml().getStringList(path);
    }

    public List<Boolean> getBoolList(String path) {
        return getYaml().getBooleanList(path);
    }

    public Object getObject(String path, Class<Object> classof) {
        return getYaml().getObject(path, classof);
    }
}
