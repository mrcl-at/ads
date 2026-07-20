package at.mrcl.ads.paper.database;

import org.jspecify.annotations.Nullable;

import java.sql.Connection;

public interface SqlDatabase extends Database {
    @Nullable Connection getConnection();
}
