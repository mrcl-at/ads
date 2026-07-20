package at.mrcl.ads.paper.database;

public interface Database {
    void connect() throws DatabaseException;
    void disconnect() throws DatabaseException;
}
