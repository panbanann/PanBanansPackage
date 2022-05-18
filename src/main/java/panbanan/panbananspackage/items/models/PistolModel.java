package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.PistolItem;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PistolModel extends AnimatedGeoModel<PistolItem> {
    @Override
    public Identifier getModelLocation(PistolItem object) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/pistol.geo.json");
    }

    @Override
    public Identifier getTextureLocation(PistolItem object) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/items/pistol.png");
    }

    @Override
    public Identifier getAnimationFileLocation(PistolItem animatable) {
        return new Identifier(PanBanansPackage.MOD_ID, "animations/pistol.animation.json");
    }
}