package panbanan.panbananspackage.items.models;

import panbanan.panbananspackage.items.PistolItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PistolRender extends GeoItemRenderer<PistolItem> {
    public PistolRender() {
        super(new PistolModel());
    }

}
