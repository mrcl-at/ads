package at.mrcl.ads;

import at.mrcl.ads.api.Bootstrapper;
import at.mrcl.ads.database.SQLiteDatabase;
import org.bukkit.Bukkit;

import java.io.File;

public class AdsPluginBootstrapper extends Bootstrapper {

    private AdsPlugin plugin;

    @Override
    public void onEnable() {
        try {
            final var config = PluginConfig.read(new File(getDataFolder(), "config.json"), PluginConfig.class);
            if (config.isCustomBootstrap()) return;

            final var database = new SQLiteDatabase(this);
            this.plugin = new AdsPlugin(this, config, database);

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
