package at.mrcl.ads.api.database;

/**
 * Represents an exception that occurs during database operations.
 * This exception serves as a custom wrapper for handling errors
 * specific to database-related functionality.
 */
public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
