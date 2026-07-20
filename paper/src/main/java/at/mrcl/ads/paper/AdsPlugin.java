package at.mrcl.ads.paper;

import at.mrcl.ads.api.AdsAPI;
import at.mrcl.ads.paper.database.Database;
import at.mrcl.ads.paper.database.DatabaseException;
import at.mrcl.ads.paper.database.sqlite.SQLiteDatabase;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class AdsPlugin extends JavaPlugin {

    private static final int BSTATS_ID = 32775;

    @Getter private PluginConfig config;
    @Getter private Database database;

    @Override
    public void onEnable() {
        try {
            AdsAPI.setApi(new ApiImpl(this));

            this.config = PluginConfig.read(this);
            this.database = initAndGetDatabase();

            new Metrics(this, BSTATS_ID);
        } catch (Exception exception) {
            getSLF4JLogger().error("Failed to enable plugin!", exception);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private Database initAndGetDatabase() throws DatabaseException {
        return switch (config.getDatabase().getType()) {
            case SQLITE -> new SQLiteDatabase(this);
            default -> throw new DatabaseException("Unsupported database type: " + config.getDatabase().getType());
        };
    }

    @Override
    public void onDisable() {
        try {
            database.disconnect();
        } catch (DatabaseException e) {
            getSLF4JLogger().warn("Failed to disconnect from database!", e);
        }
    }
}
