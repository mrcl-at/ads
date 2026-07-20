package at.mrcl.ads.paper;

import at.mrcl.ads.api.AdsAPI;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class AdsPlugin extends JavaPlugin {

    private static final int BSTATS_ID = 32775;

    private PluginConfig config;

    @Override
    public void onEnable() {
        try {
            AdsAPI.setApi(new ApiImpl(this));

            this.config = PluginConfig.read(this);

            new Metrics(this, BSTATS_ID);
        } catch (Exception exception) {
            getSLF4JLogger().error("Failed to enable plugin!", exception);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
