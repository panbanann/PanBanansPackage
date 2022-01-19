package panbanan.panbananspackage.entity.mobs;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MimicEntityRenderer extends GeoEntityRenderer<MimicEntity> {


    public MimicEntityRenderer(EntityRendererFactory.Context ctx)
    {
        super(ctx, new MimicModel());
        this.shadowRadius = 0.35F; //change 0.7 to the desired shadow size.
    }

    @Override
    public RenderLayer getRenderType(MimicEntity animatable, float partialTicks, MatrixStack stack,
                                     @Nullable VertexConsumerProvider renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
    }

    /*@Override
    protected float getDeathMaxRotation(MimicEntity entityLivingBaseIn) {
        return 0.0f;
    }*/
}
