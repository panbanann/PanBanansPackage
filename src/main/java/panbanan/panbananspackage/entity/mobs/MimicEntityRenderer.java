package panbanan.panbananspackage.entity.mobs;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class MimicEntityRenderer extends GeoEntityRenderer<MimicEntity> {


    public MimicEntityRenderer(EntityRenderDispatcher renderManager)
    {
        super(renderManager, new MimicModel());
        this.shadowRadius = 0.35F; //change 0.7 to the desired shadow size.
    }
}
