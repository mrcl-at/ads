package at.mrcl.ads.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdBuilderException extends Exception {

    private final Reason reason;

    public enum Reason {
        PUBLISHER,
        CONTENT
    }
}
