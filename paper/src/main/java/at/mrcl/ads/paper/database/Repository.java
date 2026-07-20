package at.mrcl.ads.paper.database;

import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Repository {
    Database getDatabase();
}
