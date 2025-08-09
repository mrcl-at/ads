package at.mrcl.ads.api;

public interface API {
    /**
     * Determines if the vault functionality is supported by the current API implementation.
     *
     * @return true if the vault functionality is supported, false otherwise
     */
    boolean isVaultSupported();
}
