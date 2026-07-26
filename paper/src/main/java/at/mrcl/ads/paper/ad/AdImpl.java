package at.mrcl.ads.paper.ad;

import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.api.ad.State;
import at.mrcl.ads.api.event.AdSavedEvent;
import at.mrcl.ads.paper.AdsPlugin;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@NullMarked
public class AdImpl implements Ad {

    private final UUID id;
    private final UUID publisher;

    private State state;
    private List<String> content;

    private OffsetDateTime inserted, created, edited;

    public AdImpl(UUID publisher, List<String> content) {
        this.id = UUID.randomUUID();
        this.publisher = publisher;
        this.state = State.EDITING;
        this.content = content;
        this.inserted = OffsetDateTime.now();
        this.created = OffsetDateTime.now();
        this.edited = OffsetDateTime.now();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getPublisher() {
        return publisher;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<String> getContent() {
        return content;
    }

    @Override
    public OffsetDateTime getInserted() {
        return inserted;
    }

    @Override
    public OffsetDateTime getCreated() {
        return created;
    }

    @Override
    public OffsetDateTime getEdited() {
        return edited;
    }

    @Override
    public CompletableFuture<Ad> save() {
        final var future = new CompletableFuture<Ad>();
        CompletableFuture.runAsync(() -> {
            try {
                final var plugin = AdsPlugin.getPlugin(AdsPlugin.class);
                plugin.getSLF4JLogger().info("Saving ad {}", id);
                plugin.getDatabase()
                        .getRepository()
                        .save(this);
                new AdSavedEvent(true, this).callEvent();
                future.complete(this);
            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });
        return future;
    }
}
