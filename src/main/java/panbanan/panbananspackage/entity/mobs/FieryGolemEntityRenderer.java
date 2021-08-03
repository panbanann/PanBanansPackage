package panbanan.panbananspackage.entity.mobs;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;


public class FieryGolemEntityRenderer extends GeoEntityRenderer<FieryGolemEntity> {


    public FieryGolemEntityRenderer(EntityRenderDispatcher renderManager)
    {
        super(renderManager, new FieryGolemModel());
        this.shadowRadius = 0.35F; //change 0.7 to the desired shadow size.
    }
}
