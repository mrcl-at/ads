package at.mrcl.ads;

import at.mrcl.ads.api.AdsAPI;
import at.mrcl.ads.api.Bootstrapper;
import at.mrcl.ads.api.database.Database;
import at.mrcl.ads.api.database.DatabaseException;
import org.slf4j.Logger;

public class AdsPlugin {

    private final Bootstrapper bootstrapper;
    private final Database database;
    private final PluginConfig config;

    public AdsPlugin(Bootstrapper bootstrapper, PluginConfig config, Database database) throws Exception {
        this.bootstrapper = bootstrapper;
        this.config = config;
        this.database = database;

        AdsAPI.setApi(new APIImpl(this));
    }

    public void enable() throws Exception {
        this.database.connect();
    }

    public void disable() {
        try {
            this.database.disconnect();
        } catch (DatabaseException exception) {
            getLogger().error("Failed to disconnect from the database", exception);
        }
    }

    public Logger getLogger() {
        return this.bootstrapper.getSLF4JLogger();
    }
}
