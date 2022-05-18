package panbanan.panbananspackage.entity.projectile;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestProjectileCopyPasteModel extends AnimatedGeoModel<TestProjectileCopyPaste> {
    @Override
    public Identifier getModelLocation(TestProjectileCopyPaste object) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/ball_projectile.geo.json");
    }

    @Override
    public Identifier getTextureLocation(TestProjectileCopyPaste object) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/entity/projectile/flame.png");
    }

    @Override
    public Identifier getAnimationFileLocation(TestProjectileCopyPaste animatable) {
        return new Identifier(PanBanansPackage.MOD_ID, "animations/rocket.animation.json");
    }

}