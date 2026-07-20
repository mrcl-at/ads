package at.mrcl.ads.api;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

@Getter
public class AdsAPI {

    private static API api;

    public static void setApi(@NonNull API api) {
        if (AdsAPI.api != null) throw new IllegalStateException("API already set!");
        AdsAPI.api = api;
    }

}
