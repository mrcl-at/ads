package at.mrcl.ads.paper.database;

import at.mrcl.ads.api.Ad;
import at.mrcl.ads.api.database.DatabaseException;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Repository {
    Database getDatabase();

    void save(Ad ad) throws DatabaseException;
}
