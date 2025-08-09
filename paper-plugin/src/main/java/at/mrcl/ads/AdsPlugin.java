package at.mrcl.ads;

import at.mrcl.ads.api.AdsAPI;
import at.mrcl.ads.api.Bootstrapper;
import at.mrcl.ads.api.database.Database;
import at.mrcl.ads.api.database.DatabaseException;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class AdsPlugin {

    private final Bootstrapper bootstrapper;
    private final Database database;
    private final PluginConfig config;

    @Getter private final Economy economy;

    private final AtomicBoolean enabled = new AtomicBoolean(false);

    public AdsPlugin(Bootstrapper bootstrapper, PluginConfig config, Database database) throws Exception {
        this.bootstrapper = bootstrapper;
        this.config = config;
        this.database = database;

        AdsAPI.setApi(new APIImpl(this));
        this.economy = setupVault();
    }

    public void enable() throws Exception {
        if (!this.enabled.compareAndSet(false, true)) throw new IllegalStateException("Plugin is already enabled!");
        this.database.connect();
        if (economy == null) getLogger().warn("Vault not found, disabling economy support");
    }

    public void disable() {
        if (!this.enabled.compareAndSet(true, false)) throw new IllegalStateException("Plugin is already disabled!");
        try {
            this.database.disconnect();
        } catch (DatabaseException exception) {
            getLogger().error("Failed to disconnect from the database", exception);
        }
    }

    public Logger getLogger() {
        return this.bootstrapper.getSLF4JLogger();
    }

    private Economy setupVault() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) return null;
        final var provider = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (provider == null) return null;
        return provider.getProvider();
    }
}
