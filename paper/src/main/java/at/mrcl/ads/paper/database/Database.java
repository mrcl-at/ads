package at.mrcl.ads.paper.database;

import at.mrcl.ads.api.database.DatabaseException;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Database {
    void connect() throws DatabaseException;
    void disconnect() throws DatabaseException;

    Repository getRepository();
}
