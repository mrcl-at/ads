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

    @NullMarked
    interface Builder {
        Builder publisher(Player publisher);
        Builder publisher(UUID publisher);

        Builder addLine(String line);
        Builder editLine(int index, String line);

        Ad build() throws AdBuilderException;
    }
}
