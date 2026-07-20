package at.mrcl.ads.paper;

import at.mrcl.ads.api.API;
import at.mrcl.ads.api.database.DatabaseType;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;

@AllArgsConstructor
@NullMarked
public class ApiImpl implements API {
    private final AdsPlugin plugin;

    @Override
    public DatabaseType getDatabaseType() {
        return plugin.getConfig().getDatabase().getType();
    }
}
