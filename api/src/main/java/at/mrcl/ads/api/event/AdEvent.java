package at.mrcl.ads.api.event;

import at.mrcl.ads.api.ad.Ad;
import org.bukkit.event.Event;
import org.jspecify.annotations.NullMarked;

@NullMarked
public abstract class AdEvent extends Event {

    private final Ad ad;

    public AdEvent(Ad ad) {
        this.ad = ad;
    }

    public AdEvent(boolean isAsync, Ad ad) {
        super(isAsync);
        this.ad = ad;
    }

    public Ad getAd() {
        return ad;
    }

}
