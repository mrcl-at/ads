package at.mrcl.ads.paper;

import at.mrcl.ads.api.QueueType;
import at.mrcl.ads.api.database.DatabaseType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public record PluginConfig(
        @SerializedName("queue_type") QueueType queueType,
        String timeline,
        @SerializedName("max_lines") int maxLines,
        DatabaseConfig database
) {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private static final String FILENAME = "config.json";

    public static PluginConfig read() throws IOException {
        final var file = new File(AdsPlugin.getPlugin(AdsPlugin.class).getDataFolder(), FILENAME);
        if (file.exists()) return GSON.fromJson(Files.readString(file.toPath()), PluginConfig.class);

        return new PluginConfig(
                QueueType.LIFO,
                "0-24",
                3,
                new DatabaseConfig(DatabaseType.SQLITE, null)
        ).save();
    }

    public PluginConfig save() throws IOException {
        final var plugin = AdsPlugin.getPlugin(AdsPlugin.class);
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        final var file = new File(plugin.getDataFolder(), FILENAME);
        if (!file.exists()) file.createNewFile();
        Files.writeString(file.toPath(), GSON.toJson(this));
        return this;
    }

    public record DatabaseConfig(
            DatabaseType type,
            String url
    ) {
    }
}
