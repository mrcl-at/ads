package at.mrcl.ads.paper.ad;

import at.mrcl.ads.api.AdsAPI;
import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.api.ad.AdBuilderException;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class BuilderImpl implements Ad.Builder {

    private UUID publisher;
    private List<String> content;

    @Override
    public Ad.@NonNull Builder publisher(@NonNull Player publisher) {
        this.publisher = publisher.getUniqueId();
        return this;
    }

    @Override
    public Ad.@NonNull Builder publisher(@NonNull UUID publisher) {
        this.publisher = publisher;
        return this;
    }

    @Override
    public Ad.@NonNull Builder line(@NonNull String line) {
        if (this.content == null) this.content = new ArrayList<>(AdsAPI.getApi().getMaxLines());
        this.content.add(line);
        return this;
    }

    @Override
    public Ad.@NonNull Builder line(int index, @NonNull String line) {
        if (index >= AdsAPI.getApi().getMaxLines()) throw new ArrayIndexOutOfBoundsException();
        if (index < 0) throw new IllegalArgumentException();
        if (this.content == null) this.content = new ArrayList<>(AdsAPI.getApi().getMaxLines());
        this.content.set(index, line);
        return this;
    }

    @Override
    public @NonNull CompletableFuture<Ad> build() throws AdBuilderException {
        if (publisher == null) throw new AdBuilderException(AdBuilderException.Reason.PUBLISHER);
        if (content == null || content.isEmpty()) throw new AdBuilderException(AdBuilderException.Reason.CONTENT);
        final var ad = new AdImpl(publisher, content);
        return ad.save().thenApply(result -> result ? ad : null);
    }
}
