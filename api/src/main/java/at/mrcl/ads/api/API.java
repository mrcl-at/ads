package at.mrcl.ads.api;

import at.mrcl.ads.api.database.DatabaseType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface API {
    DatabaseType getDatabaseType();

    Ad.Builder buildAd();
}
