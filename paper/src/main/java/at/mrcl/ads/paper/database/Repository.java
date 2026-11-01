package at.mrcl.ads.paper.database;

import at.mrcl.ads.api.Ad;
import at.mrcl.ads.api.database.DatabaseException;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Repository {
    Database getDatabase();

    boolean insert(Ad ad) throws DatabaseException;
    boolean delete(Ad ad) throws DatabaseException;
}
