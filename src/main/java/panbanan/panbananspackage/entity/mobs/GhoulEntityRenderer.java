package panbanan.panbananspackage.entity.mobs;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class GhoulEntityRenderer extends GeoEntityRenderer<GhoulEntity> {


    public GhoulEntityRenderer(EntityRenderDispatcher renderManager)
    {
        super(renderManager, new GhoulModel());
        this.shadowRadius = 0.2F; //change 0.7 to the desired shadow size.
    }

    @Override
    protected float getDeathMaxRotation(GhoulEntity entityLivingBaseIn) {
        return 0.0f;
    }
}
