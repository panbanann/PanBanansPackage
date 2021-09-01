package panbanan.panbananspackage.entity.mobs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class MimicModel extends AnimatedGeoModel {

    @Override
    public Identifier getModelLocation(Object object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/mimic.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/mobs/mimic.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Object object)
    {
        return new Identifier("panbananspackage", "animations/mimic.animation.json");
    }


}