package at.mrcl.ads.paper;

import at.mrcl.ads.api.database.DatabaseType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
@Getter
public class PluginConfig {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private static final String FILENAME = "config.json";

    private final DatabaseConfig database;

    public static PluginConfig read(@NonNull JavaPlugin plugin) throws IOException {
        final var file = new File(plugin.getDataFolder(), FILENAME);
        if (file.exists()) return GSON.fromJson(Files.readString(file.toPath()), PluginConfig.class);

        return new PluginConfig(
                new DatabaseConfig(DatabaseType.SQLITE, null)
        ).save(plugin);
    }

    public PluginConfig save(@NonNull JavaPlugin plugin) throws IOException {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        final var file = new File(plugin.getDataFolder(), FILENAME);
        if (!file.exists()) file.createNewFile();
        Files.writeString(file.toPath(), GSON.toJson(this));
        return this;
    }

    @AllArgsConstructor
    @Getter
    public static class DatabaseConfig {
        private final DatabaseType type;
        private final String url;
    }
}
