package at.mrcl.ads.api;

import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@NullMarked
public interface Ad {
    UUID getId();
    UUID getPublisher();

    State getState();
    List<String> getContent();

    OffsetDateTime getInserted();
    OffsetDateTime getCreated();
    OffsetDateTime getEdited();

    @NullMarked
    interface Builder {
        Builder publisher(Player publisher);
        Builder publisher(UUID publisher);

        Builder addLine(String line);
        Builder editLine(int index, String line);

        Ad build() throws AdBuilderException;
    }
}
