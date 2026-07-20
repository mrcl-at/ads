package at.mrcl.ads.paper.database.sqlite;

import at.mrcl.ads.paper.database.Database;
import at.mrcl.ads.paper.database.Repository;
import at.mrcl.ads.paper.database.SqlDatabase;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SQLiteRepository implements Repository {

    private final SqlDatabase database;

    public SQLiteRepository( SqlDatabase database) {
        this.database = database;
    }

    @Override
    public Database getDatabase() {
        return database;
    }
}
