package at.mrcl.ads.paper;

import at.mrcl.ads.api.API;
import at.mrcl.ads.api.ad.Ad;
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
        return plugin.getConfig().getMaxLines();
    }

    @Override
    public DatabaseType getDatabaseType() {
        return plugin.getConfig().getDatabase().getType();
    }

    @Override
    public String getTimeline() {
        return plugin.getConfig().getTimeline();
    }

    @Override
    public QueueType getQueueType() {
        return plugin.getConfig().getQueueType();
    }

    @Override
    public Ad.Builder builder() {
        return new BuilderImpl();
    }
}
