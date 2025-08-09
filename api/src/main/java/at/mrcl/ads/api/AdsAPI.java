package at.mrcl.ads.api;

public class AdsAPI {

    private static API api;

    public static void setApi(API api) {
        if (AdsAPI.api != null) throw new IllegalStateException("API already set");
        AdsAPI.api = api;
    }

    public static API getApi() {
        return api;
    }

    /**
     * Determines if the vault functionality is supported by the current API implementation.
     *
     * @return true if the vault functionality is supported, false otherwise
     */
    public static boolean isVaultSupported() {
        return api.isVaultSupported();
    }
}
