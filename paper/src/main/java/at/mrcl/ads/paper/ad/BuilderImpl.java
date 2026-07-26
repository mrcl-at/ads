package at.mrcl.ads.paper.ad;

import at.mrcl.ads.api.ad.Ad;
import at.mrcl.ads.api.ad.AdBuilderException;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.*;

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
    public Ad.@NonNull Builder addLine(@NonNull String line) {
        if (this.content == null) this.content = new ArrayList<>();
        this.content.add(line);
        return this;
    }

    @Override
    public Ad.@NonNull Builder editLine(int index, @NonNull String line) {
        if (index < 0) throw new IllegalArgumentException();
        if (this.content == null) this.content = new ArrayList<>(++index);
        if (this.content.size() - 1 < index) throw new ArrayIndexOutOfBoundsException();
        this.content.set(index, line);
        return this;
    }

    @Override
    public @NonNull Ad build() throws AdBuilderException {
        if (publisher == null) throw new AdBuilderException(AdBuilderException.Reason.PUBLISHER);
        if (content == null || content.isEmpty()) throw new AdBuilderException(AdBuilderException.Reason.CONTENT);
        final var ad = new AdImpl(publisher, content);
        // TODO save
        return ad;
    }
}
