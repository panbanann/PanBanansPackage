package panbanan.panbananspackage.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntityRenderer;
import panbanan.panbananspackage.entity.mobs.GhoulEntityRenderer;
import panbanan.panbananspackage.entity.mobs.MimicEntityRenderer;
import panbanan.panbananspackage.entity.mobs.NiddhogEntityRenderer;
import panbanan.panbananspackage.items.ItemRegistry;
import panbanan.panbananspackage.items.armors.ArmorSet;
import panbanan.panbananspackage.items.models.PotatoRenderer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.security.Key;

@SuppressWarnings("deprecation")
@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class PanBanansPackageClient implements ClientModInitializer {

    @SuppressWarnings({ "unchecked" })
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.INSTANCE.register(EntityRegister.FIERY_GOLEM, (context) -> new FieryGolemEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.MIMIC, (context) -> new MimicEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.NIDDHOG, (context) -> new NiddhogEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.GHOUL, (context) -> new GhoulEntityRenderer(context));

        GeoArmorRenderer.registerArmorRenderer(new PotatoRenderer(), ItemRegistry.POTAT_HELMET, ItemRegistry.POTAT_CHEST, ItemRegistry.POTAT_LEGGINGS, ItemRegistry.POTAT_BOOTS);
    }
}
