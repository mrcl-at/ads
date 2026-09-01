package at.mrcl.ads.api.event;

import at.mrcl.ads.api.ad.Ad;
import org.bukkit.event.HandlerList;

public class AdDeletedEvent extends AdEvent {

    public AdDeletedEvent(Ad ad) {
        super(ad);
    }

    public AdDeletedEvent(boolean isAsync, Ad ad) {
        super(isAsync, ad);
    }

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}