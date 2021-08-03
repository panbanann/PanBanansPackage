package panbanan.panbananspackage.entity.mobs;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class FieryGolemModel extends AnimatedGeoModel<FieryGolemEntity> {

    @Override
    public Identifier getModelLocation(FieryGolemEntity object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/fiery_golem.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FieryGolemEntity object)
    {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/mobs/fiery_golem.png");
    }

    @Override
    public Identifier getAnimationFileLocation(FieryGolemEntity object)
    {
        return new Identifier("panbananspackage", "animations/fiery_golem.animation.json");
    }

}
