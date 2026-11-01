package at.mrcl.ads.paper;

import at.mrcl.ads.api.API;
import at.mrcl.ads.api.Ad;
import at.mrcl.ads.api.QueueType;
import at.mrcl.ads.api.database.DatabaseType;
import at.mrcl.ads.paper.ad.BuilderImpl;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;

@AllArgsConstructor
@NullMarked
public class ApiImpl implements API {
    private final AdsPlugin plugin;

    @Override
    public int getMaxLines() {
        return plugin.getConfig().maxLines();
    }

    @Override
    public DatabaseType getDatabaseType() {
        return plugin.getConfig().database().type();
    }

    @Override
    public String getTimeline() {
        return plugin.getConfig().timeline();
    }

    @Override
    public QueueType getQueueType() {
        return plugin.getConfig().queueType();
    }

    @Override
    public Ad.Builder builder() {
        return new BuilderImpl();
    }
}
