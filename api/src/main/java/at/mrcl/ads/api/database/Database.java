package at.mrcl.ads.api.database;

/**
 * Represents a generic interface for interacting with a database.
 * Implementations of this interface are responsible for managing
 * connections and ensuring proper resource handling during
 * interactions with the database.
 */
public interface Database {
    /**
     * Establishes a connection to the database.
     * This method must be invoked before performing any database operations.
     *
     * @throws DatabaseException if there is an error while attempting to connect to the database.
     */
    void connect() throws DatabaseException;
    /**
     * Terminates the connection to the database.
     *
     * @throws DatabaseException if an error occurs while attempting to disconnect.
     */
    void disconnect() throws DatabaseException;
}
