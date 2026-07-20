package at.mrcl.ads.api;

import lombok.Getter;

@Getter
public class AdsAPI {

    private static API api;

    public static void setApi(API api) {
        if (AdsAPI.api != null) throw new IllegalStateException("API already set!");
        AdsAPI.api = api;
    }

}
