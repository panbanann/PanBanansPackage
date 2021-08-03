package panbanan.panbananspackage.client;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntityRenderer;
import panbanan.panbananspackage.items.armors.potatArmorSet;
import panbanan.panbananspackage.items.models.PotatoRenderer;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

public class RenderRegister {

    public static void onInitialize(){
        //Entities render registry
        EntityRendererRegistry.INSTANCE.register(EntityRegister.FIERY_GOLEM, (dispatcher, context) -> new FieryGolemEntityRenderer(dispatcher));

        //Armors render registry
        GeoArmorRenderer.registerArmorRenderer(potatArmorSet.class, new PotatoRenderer());
    }
}
