package at.mrcl.ads.paper.database.sqlite;

import at.mrcl.ads.paper.AdsPlugin;
import at.mrcl.ads.paper.database.DatabaseException;
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

    public SQLiteDatabase(AdsPlugin plugin) {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        this.url = URL_PREFIX + new File(plugin.getDataFolder(), "ads.db").getPath();
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
}
