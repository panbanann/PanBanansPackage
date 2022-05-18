package panbanan.panbananspackage.entity.projectile;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import software.bernie.example.client.model.entity.RocketModel;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class TestProjectileCopyPasteRenderer extends GeoProjectilesRenderer<TestProjectileCopyPaste> {

    public TestProjectileCopyPasteRenderer(EntityRendererFactory.Context renderManagerIn) {
        super(renderManagerIn, new TestProjectileCopyPasteModel());
    }

    protected int getBlockLight(TestProjectileCopyPaste entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderLayer getRenderType(TestProjectileCopyPaste animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}