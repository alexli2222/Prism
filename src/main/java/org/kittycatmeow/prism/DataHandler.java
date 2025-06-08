package org.kittycatmeow.chance;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class DataHandler {
    private final File file;
    private final YamlConfiguration config;

    public DataHandler() throws IOException {
        File folder = Chance.getPlugin().getDataFolder();
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Could not create plugin directory");
        }

        file = new File(folder, "data.yml");
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Could not create data file");
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String key, @Nullable String value) {
        config.set(key, value);
    }

    public String get(String key) {
        return config.getString(key);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}