package at.mrcl.ads.paper.database;

import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.api.database.DatabaseException;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Repository {
    Database getDatabase();

    boolean save(Ad ad) throws DatabaseException;
    boolean delete(Ad ad) throws DatabaseException;
}
