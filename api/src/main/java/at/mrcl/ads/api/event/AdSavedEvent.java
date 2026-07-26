package at.mrcl.ads.api.event;

import at.mrcl.ads.api.ad.Ad;
import org.bukkit.event.HandlerList;

public class AdSavedEvent extends AdEvent {

    public AdSavedEvent(Ad ad) {
        super(ad);
    }

    public AdSavedEvent(boolean isAsync, Ad ad) {
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
