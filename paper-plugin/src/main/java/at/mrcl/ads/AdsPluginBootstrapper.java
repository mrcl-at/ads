package at.mrcl.ads;

import at.mrcl.ads.api.Bootstrapper;
import at.mrcl.ads.database.SQLiteDatabase;
import org.bukkit.Bukkit;

public class AdsPluginBootstrapper extends Bootstrapper {

    private AdsPlugin plugin;

    @Override
    public void onEnable() {
        try {
            final var database = new SQLiteDatabase(this);
            this.plugin = new AdsPlugin(this, database);

            this.plugin.enable();
        } catch (Exception exception) {
            getSLF4JLogger().error("Failed to enable plugin", exception);
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        this.plugin.disable();
    }
}
