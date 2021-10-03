package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class NiddhogModel extends AnimatedGeoModel {

    @Override
    public Identifier getModelLocation(Object o) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/niddhog.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object o) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/mobs/niddhog.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Object o) {
        return new Identifier("panbananspackage", "animations/niddhog.animations.json");
    }

    @Override
    public void setLivingAnimations(IAnimatable entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

    @Override
    public IBone getBone(String boneName) {
        return super.getBone(boneName);
    }

}
