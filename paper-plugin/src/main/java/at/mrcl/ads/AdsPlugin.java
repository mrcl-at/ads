package at.mrcl.ads;

import at.mrcl.ads.api.AdsAPI;
import at.mrcl.ads.api.database.Database;
import at.mrcl.ads.api.database.DatabaseException;
import at.mrcl.ads.database.SQLiteDatabase;
import org.bukkit.plugin.java.JavaPlugin;

public class AdsPlugin extends JavaPlugin {

    private Database database;

    @Override
    public void onEnable() {
        try {
            AdsAPI.setApi(new APIImpl(this));
            this.database = new SQLiteDatabase(this);

            this.database.connect();
        } catch (Exception exception) {
            getSLF4JLogger().error("Failed to enable plugin", exception);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            this.database.disconnect();
        } catch (DatabaseException exception) {
            getSLF4JLogger().error("Failed to disconnect from the database", exception);
        }
    }
}
