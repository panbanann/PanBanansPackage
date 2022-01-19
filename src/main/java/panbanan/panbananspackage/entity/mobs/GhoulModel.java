package panbanan.panbananspackage.entity.mobs;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

@SuppressWarnings("rawtypes")
public class GhoulModel extends AnimatedGeoModel {

    @Override
    public Identifier getModelLocation(Object o) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/ghoul.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object o) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/mobs/ghoul.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Object o) {
        return new Identifier("panbananspackage", "animations/ghoul.animation.json");
    }

    @Override
    public void setLivingAnimations(Object entity, Integer integer, AnimationEvent animationEvent) {
        super.setLivingAnimations((IAnimatable) entity, integer, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }

    @Override
    public IBone getBone(String boneName) {
        return super.getBone(boneName);
    }

}