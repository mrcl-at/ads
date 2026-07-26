package at.mrcl.ads.paper.database.sqlite;

import at.mrcl.ads.paper.AdsPlugin;
import at.mrcl.ads.api.database.DatabaseException;
import at.mrcl.ads.paper.database.Repository;
import at.mrcl.ads.paper.database.SqlDatabase;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NullMarked
public class SQLiteDatabase implements SqlDatabase {

    private static final String URL_PREFIX = "jdbc:sqlite:";

    private @Nullable Connection connection;
    private final String url;

    private final Repository repository;

    public SQLiteDatabase(AdsPlugin plugin) {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        this.url = URL_PREFIX + new File(plugin.getDataFolder(), "ads.db").getPath();
        this.repository = new SQLiteRepository(this);
    }

    @Override
    public @Nullable Connection getConnection() {
        return connection;
    }

    @Override
    public void connect() throws DatabaseException {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                disconnect();
            }
            this.connection = DriverManager.getConnection(url);
            this.connection.createStatement()
                    .execute("CREATE TABLE IF NOT EXISTS ads (id VARCHAR(36) PRIMARY KEY, publisher VARCHAR(36), state TEXT, content TEXT, created INTEGER, inserted INTEGER, edited INTEGER)");
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    @Override
    public void disconnect() throws DatabaseException {
        try {
            if (this.connection == null) return;
            if (this.connection.isClosed()) return;
            this.connection.close();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    @Override
    public Repository getRepository() {
        return repository;
    }
}
