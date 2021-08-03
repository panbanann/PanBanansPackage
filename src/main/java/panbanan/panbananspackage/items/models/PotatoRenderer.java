package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.armors.potatArmorSet;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

public class PotatoRenderer extends GeoArmorRenderer<potatArmorSet> {

    public PotatoRenderer(){
        super(new AnimatedGeoModel<potatArmorSet>() {
            @Override
            public Identifier getModelLocation(potatArmorSet potatArmorSet) {
                return new Identifier(PanBanansPackage.MOD_ID, "geo/potat.geo.json");
            }

            @Override
            public Identifier getTextureLocation(potatArmorSet potatArmorSet) {
                return new Identifier(PanBanansPackage.MOD_ID, "textures/armor/potat_texture.png");
            }

            @Override
            public Identifier getAnimationFileLocation(potatArmorSet potatArmorSet) {
                return null;
            }
        });

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";

    }

    /*public static void onInitializeClient()
    {
        GeoArmorRenderer.registerArmorRenderer(potatHelmet.class, new PotatoRenderer());
    }*/


}
