package at.mrcl.ads;

import at.mrcl.ads.api.AdsAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class AdsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            AdsAPI.setApi(new APIImpl(this));
        } catch (Exception exception) {
            getSLF4JLogger().error("Failed to enable plugin", exception);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
