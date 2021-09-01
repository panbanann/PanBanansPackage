package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;


public class FieryGolemModel extends AnimatedGeoModel {

    @Override
    public Identifier getModelLocation(Object object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/fiery_golem.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/mobs/fiery_golem.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Object object)
    {
        return new Identifier("panbananspackage", "animations/fiery_golem.animation.json");
    }

    @Override
    public void setLivingAnimations(IAnimatable entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }


}
