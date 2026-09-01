package at.mrcl.ads.api.ad;

import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@NullMarked
public interface Ad {
    UUID getId();
    UUID getPublisher();

    State getState();
    List<String> getContent();

    OffsetDateTime getInserted();
    OffsetDateTime getCreated();
    OffsetDateTime getEdited();

    CompletableFuture<Ad> save();
    CompletableFuture<Boolean> delete();

    @NullMarked
    interface Builder {
        Builder publisher(Player publisher);
        Builder publisher(UUID publisher);

        Builder line(String line);
        Builder line(int index, String line);

        CompletableFuture<Ad> build() throws AdBuilderException;
    }
}
