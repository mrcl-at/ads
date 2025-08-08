package at.mrcl.ads;

import at.mrcl.ads.util.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PluginConfig extends Config {

    private boolean customBootstrap = false;

}
