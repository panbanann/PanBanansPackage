package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.armors.ArmorSet;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SattesModel extends AnimatedGeoModel<ArmorSet> {
    @Override
    public Identifier getModelLocation(ArmorSet ArmorSet) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/sattes_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ArmorSet ArmorSet) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/armor/temp.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ArmorSet ArmorSet) {
        return null;
    }

}
