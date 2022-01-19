package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.armors.ArmorSet;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PotatoModel extends AnimatedGeoModel<ArmorSet> {
    @Override
    public Identifier getModelLocation(ArmorSet ArmorSet) {
        return new Identifier(PanBanansPackage.MOD_ID, "geo/potat.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ArmorSet ArmorSet) {
        return new Identifier(PanBanansPackage.MOD_ID, "textures/armor/potat_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ArmorSet ArmorSet) {
        return null;
    }

}
