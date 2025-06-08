package org.kittycatmeow.prism;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class FirstJoinDataHandler {
    private final File file;
    private final YamlConfiguration config;

    public FirstJoinDataHandler() throws IOException {
        File folder = Prism.getPlugin().getDataFolder();
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Could not create plugin directory");
        }

        file = new File(folder, "firstJoinData.yml");
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Could not create data file");
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String key, boolean value) {
        config.set(key, value);
    }

    public boolean get(String key) {
        return config.getBoolean(key);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}