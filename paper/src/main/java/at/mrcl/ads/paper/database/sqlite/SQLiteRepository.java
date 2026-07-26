package at.mrcl.ads.paper.database.sqlite;

import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.paper.database.Database;
import at.mrcl.ads.api.database.DatabaseException;
import at.mrcl.ads.paper.database.Repository;
import at.mrcl.ads.paper.database.SqlDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SQLiteRepository implements Repository {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private final SqlDatabase database;

    public SQLiteRepository(SqlDatabase database) {
        this.database = database;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public void save(Ad ad) throws DatabaseException {
        try {
            final var statement = this.database
                    .getConnection()
                    .prepareStatement("INSERT INTO ads (id, publisher, state, content, created, inserted, edited) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, ad.getId().toString());
            statement.setString(2, ad.getPublisher().toString());
            statement.setString(3, ad.getState().name());
            statement.setString(4, GSON.toJson(ad.getContent()));
            statement.setLong(5, ad.getCreated().toEpochSecond());
            statement.setLong(6, ad.getInserted().toEpochSecond());
            statement.setLong(7, ad.getEdited().toEpochSecond());
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new DatabaseException(exception);
        }
    }
}
