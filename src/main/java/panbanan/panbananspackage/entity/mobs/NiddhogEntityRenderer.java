package panbanan.panbananspackage.entity.mobs;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class NiddhogEntityRenderer extends GeoEntityRenderer<NiddhogEntity> {

    public NiddhogEntityRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new NiddhogModel());
        this.shadowRadius = 2.5F;
    }
    @Override
    protected float getDeathMaxRotation(NiddhogEntity entityLivingBaseIn) {
        return 0.0f;
    }
}
