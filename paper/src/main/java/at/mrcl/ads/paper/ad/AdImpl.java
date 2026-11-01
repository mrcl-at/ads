package at.mrcl.ads.paper.ad;

import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.api.ad.State;
import at.mrcl.ads.api.event.AdDeletedEvent;
import at.mrcl.ads.api.event.AdSavedEvent;
import at.mrcl.ads.paper.AdsPlugin;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
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
    public CompletableFuture<Boolean> save() {
        final var future = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                final var plugin = AdsPlugin.getPlugin(AdsPlugin.class);
                plugin.getSLF4JLogger().info("Saving ad {}", id);
                final var result = plugin.getDatabase()
                        .getRepository()
                        .insert(this);
                if (result) {
                    new AdSavedEvent(true, this).callEvent();
                } else {
                    plugin.getSLF4JLogger().warn("Failed to save ad {}", id);
                }
                future.complete(result);
            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<Boolean> delete() {
        final var future = new CompletableFuture<Boolean>();
        CompletableFuture.runAsync(() -> {
            try {
                final var plugin = AdsPlugin.getPlugin(AdsPlugin.class);
                plugin.getSLF4JLogger().info("Deleting ad {}", id);
                final var result = plugin.getDatabase()
                        .getRepository()
                        .delete(this);
                if (result) {
                    new AdDeletedEvent(true, this).callEvent();
                } else {
                    plugin.getSLF4JLogger().warn("Failed to delete ad {}", id);
                }
                future.complete(result);
            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });
        return future;
    }

    @Override
    public void broadcast() {
        if (this.content == null) throw new NullPointerException("Content cannot be null");
        if (this.content.isEmpty()) throw new IllegalArgumentException("Content cannot be empty");
        final var message = MiniMessage.miniMessage().deserialize(parseContent());
        Bukkit.broadcast(message);
    }

    private String parseContent() {final var stringBuilder = new StringBuilder();
        for (int i = 0; i < this.content.size(); i++) {
            final var line = this.content.get(i);
            if (line == null) continue;

            if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
            stringBuilder.append(line);
        }
        if (stringBuilder.isEmpty()) throw new IllegalArgumentException("Content cannot be empty");
        return stringBuilder.toString();
    }
}
